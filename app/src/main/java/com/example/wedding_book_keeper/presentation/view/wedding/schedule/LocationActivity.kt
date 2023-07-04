package com.example.wedding_book_keeper.presentation.view.wedding.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.request.WeddingCreateRequest
import com.example.wedding_book_keeper.databinding.ActivityLocationBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.introduction.IntroductionActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationActivity : BaseActivity<ActivityLocationBinding>(R.layout.activity_location) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            val weddingLocation = binding.editLocation.text.toString()
            val weddingDate = getWeddingDate()
            saveWeddingInfo(weddingLocation, weddingDate)

            val intent = Intent(this, IntroductionActivity::class.java);
            startActivity(intent)
        }
    }

    private fun getWeddingDate(): String? {
        return intent.getStringExtra("weddingDate")
    }

    private fun saveWeddingInfo(weddingLocation: String?, weddingDate: String?) {
        Log.d("TAG", "saveWeddingInfo: $weddingLocation, $weddingDate")
        weddingDate?.let {
            weddingLocation?.let {
                WeddingBookKeeperClient.weddingService.createWedding(
                    WeddingCreateRequest(
                        weddingLocation,
                        weddingDate
                    )
                ).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            val intent = Intent(this@LocationActivity, IntroductionActivity::class.java)
                            startActivity(intent)
                            return;
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("TAG", "onFailure: ${t.message}")
                    }
                })
            }
        }
    }
}
