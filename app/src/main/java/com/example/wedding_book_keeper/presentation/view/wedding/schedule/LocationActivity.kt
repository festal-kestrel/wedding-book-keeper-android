package com.example.wedding_book_keeper.presentation.view.wedding.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.request.WeddingCreateRequest
import com.example.wedding_book_keeper.data.remote.response.WeddingCreateResponse
import com.example.wedding_book_keeper.databinding.ActivityLocationBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.introduction.NewIntroActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationActivity : BaseActivity<ActivityLocationBinding>(R.layout.activity_location) {
    private var txtCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtCode = intent.getStringExtra("txtCode").toString()
        Log.d("txtCode", "스케쥴화면, 받아온 값 : $txtCode")
        initEvent()
    }

    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            val weddingLocation = binding.editLocation.text.toString()
            val weddingDate = getWeddingDate()
            saveWeddingInfo(weddingLocation, weddingDate)
        }
        binding.btnGoBack.setOnClickListener{
            finish()
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
                ).enqueue(object : Callback<WeddingCreateResponse> {
                    override fun onResponse(call: Call<WeddingCreateResponse>, response: Response<WeddingCreateResponse>) {
                        if (response.isSuccessful) {
                            WeddingBookKeeperApplication.prefs.weddingId = response.body()?.weddingId!!
                            val intent = Intent(this@LocationActivity, NewIntroActivity::class.java)
                            intent.putExtra("txtCode", txtCode)

                            startActivity(intent)
                            return;
                        }
                    }

                    override fun onFailure(call: Call<WeddingCreateResponse>, t: Throwable) {
                        Log.d("TAG", "onFailure: ${t.message}")
                    }
                })
            }
        }
    }
}
