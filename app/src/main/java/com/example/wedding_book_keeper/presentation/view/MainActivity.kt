package com.example.wedding_book_keeper.presentation.view

import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.databinding.ActivityMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRetrofitTest()
    }

    private fun initRetrofitTest() {
        WeddingBookKeeperClient.weddingService.getWeddingInfo(74).enqueue(object : Callback<WeddingInfoResponse> {
            override fun onResponse(call: Call<WeddingInfoResponse>, response: Response<WeddingInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    showToastMessage("성공")

                    // WeddingInfoResponse의 groomName, brideName 조회
                    Log.d(TAG, "groomName: ${response.body()?.groomName}")
                    Log.d(TAG, "brideName: ${response.body()?.brideName}")
                    return
                } else {
                    Log.e(TAG, "$response")
                }
                showToastMessage("실패")
            }

            override fun onFailure(call: Call<WeddingInfoResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                showToastMessage("실패")
            }
        })
    }
}
