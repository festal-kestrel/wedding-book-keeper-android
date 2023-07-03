package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import com.bumptech.glide.Glide
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.data.remote.response.WeddingQrResponse
import com.example.wedding_book_keeper.databinding.ActivityGuestEntryQractivityBinding
import com.example.wedding_book_keeper.databinding.ActivityWeddingDatePickerBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestEntryQRActivity : BaseActivity<ActivityGuestEntryQractivityBinding>(R.layout.activity_guest_entry_qractivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }
        getWeddingQr(90)
    }

    private fun getWeddingQr(weddingId: Int) {
        WeddingBookKeeperClient.weddingService.getWeddingQr(weddingId).enqueue(object : Callback<WeddingQrResponse> {
            override fun onResponse(call: Call<WeddingQrResponse>, response: Response<WeddingQrResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("qrImgUrl", it.qrImgUrl)
                        editor.apply()
                        Log.d("hong", "onResponse: ${response.body()}")
                        showToastMessage("성공")

                        updateUI()
                    }
                }
            }

            override fun onFailure(call: Call<WeddingQrResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val qrImgUrl = sharedPref.getString("qrImgUrl", null)

        Glide.with(this)
            .load(qrImgUrl)
            .into(binding.imgQr)

    }

}
