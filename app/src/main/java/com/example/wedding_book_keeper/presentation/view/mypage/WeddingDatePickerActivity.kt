package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.request.WeddingUpdateInformationRequest
import com.example.wedding_book_keeper.databinding.ActivityWeddingDatePickerBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WeddingDatePickerActivity : BaseActivity<ActivityWeddingDatePickerBinding>(R.layout.activity_wedding_date_picker) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.btnToolbarConfirm.setOnClickListener {

            val selectedDate = getSelectedDate()
            val selectedTime = getSelectedTime()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate)
            val formattedTime = timeFormat.format(selectedTime)
            println("Selected Date: $formattedDate")
            println("Selected Time: $formattedTime")

            var newLocation = binding.txtNewLocation.text.toString()
            var newDateTime = formattedDate + " " + formattedTime

            updateWeddingInfo(WeddingBookKeeperApplication.prefs.weddingId, WeddingUpdateInformationRequest(location = newLocation, newDateTime))

            var intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getSelectedDate(): Date {
        val year = binding.datePicker.year
        val month = binding.datePicker.month
        val dayOfMonth = binding.datePicker.dayOfMonth

        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.time
    }

    private fun getSelectedTime(): Date {
        val hour = binding.timePicker.hour
        val minute = binding.timePicker.minute

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.time
    }


    private fun updateWeddingInfo(weddingId: Int, weddingUpdateInformationRequest: WeddingUpdateInformationRequest) {
        WeddingBookKeeperClient.weddingService.updateWeddingInfo(weddingId, weddingUpdateInformationRequest)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            Log.d("Wedding", "성공")
                        }
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("updateWeddingInfo", "Error: ${t.message}")
                }
            })
    }
}
