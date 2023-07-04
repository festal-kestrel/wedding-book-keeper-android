package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.databinding.ActivityCoupleMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.PartnerConnectActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class CoupleMyPageActivity : BaseActivity<ActivityCoupleMyPageBinding>(R.layout.activity_couple_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.layoutRowWeddingInfo.setOnClickListener {
            var intent = Intent(this, WeddingDatePickerActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowQr.setOnClickListener {
            var intent = Intent(this, GuestEntryQRActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowAdminCode.setOnClickListener {
            var intent = Intent(this, AdminCodeActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowPartnerRegister.setOnClickListener {
            var intent = Intent(this, PartnerConnectActivity::class.java)
            startActivity(intent)
        }

        fun onClick(habitId: Long) {
        }

        binding.btnToolbarSeeMore.setOnClickListener {
            ChangeRoleFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, ChangeRoleFragment.TAG)
        }
        getWeddingInfo(90)
    }

    private fun getWeddingInfo(weddingId: Int) {
        WeddingBookKeeperClient.weddingService.getWeddingInfo(weddingId).enqueue(object : Callback<WeddingInfoResponse> {
            override fun onResponse(call: Call<WeddingInfoResponse>, response: Response<WeddingInfoResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("groomName", it.groomName)
                        editor.putString("brideName", it.brideName)
                        editor.putString("location", it.location)
                        editor.putString("weddingDate", it.weddingDate?.let { it1 -> formatDate(it1) })
                        editor.apply()
                        Log.d("hong", "onResponse: ${response.body()}")

                        // WeddingInfoResponse의 groomName, brideName 조회
                        Log.d("hong", "groomName: ${response.body()?.groomName}")
                        Log.d("hong", "brideName: ${response.body()?.brideName}")
                        updateUI()
                    }
                }
            }

            override fun onFailure(call: Call<WeddingInfoResponse>, t: Throwable) {
                // handle error
            }
        })
    }

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val groomName = sharedPref.getString("groomName", null)
        val brideName = sharedPref.getString("brideName", null)
        val weddingDate = sharedPref.getString("weddingDate", null)
        val location = sharedPref.getString("location", null)

        if (groomName != null) {
            this.binding.txtMypageGroom.text = groomName
        }
        if (brideName != null) {
            this.binding.txtMypageBride.text = brideName
        }
        if (weddingDate != null) {
            this.binding.txtWeddingdate.text = weddingDate
        }
        if (location != null) {
            this.binding.txtLocation.text = location
        }
    }

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}
