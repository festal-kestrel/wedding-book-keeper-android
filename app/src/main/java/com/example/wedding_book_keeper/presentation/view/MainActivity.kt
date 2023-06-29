package com.example.wedding_book_keeper.presentation.view

import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.TestResponse
import com.example.wedding_book_keeper.databinding.ActivityMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
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
        WeddingBookKeeperClient.weddingApi.getTestApi().enqueue(object : Callback<TestResponse> {
            override fun onResponse(call: Call<TestResponse>, response: Response<TestResponse>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    showToastMessage("성공")

                    // TestResponse의 groomName, brideName 조회
                    Log.d(TAG, "onResponse: ${response.body()?.groomName}")
                    Log.d(TAG, "onResponse: ${response.body()?.brideName}")
                    return;
                }
                showToastMessage("실패")
            }

            override fun onFailure(call: Call<TestResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                showToastMessage("실패")
            }
        })
    }
}
