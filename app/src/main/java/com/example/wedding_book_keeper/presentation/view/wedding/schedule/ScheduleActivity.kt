package com.example.wedding_book_keeper.presentation.view.wedding.schedule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityScheduleBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.PartnerConnectActivity
import java.util.Locale


class ScheduleActivity : BaseActivity<ActivityScheduleBinding>(R.layout.activity_schedule) {
    private lateinit var txtCode: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initEvent()
    }

    private fun initView() {
        binding.txtSelectedDate.text =
            "${binding.dpWeddingDate.year}년 ${binding.dpWeddingDate.month + 1}월 ${binding.dpWeddingDate.dayOfMonth}일"
        binding.txtSelectedTime.text = formatTime(binding.tpWeddingTime.hour, binding.tpWeddingTime.minute)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            txtCode = intent.getStringExtra("txtCode").toString()
            Log.d("txtCode", "받아온 값 : " + intent.getStringExtra("txtCode"))
            val intent = Intent(this, LocationActivity::class.java);
            intent.putExtra("weddingDate", formatWeddingDate())
            intent.putExtra("txtCode", txtCode)

            startActivity(intent)
        }
        binding.btnGoBack.setOnClickListener {
            finish()
        }

        binding.dpWeddingDate.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            binding.txtSelectedDate.text = "${year}년 ${monthOfYear + 1}월 ${dayOfMonth}일"
        }

        binding.tpWeddingTime.setOnTimeChangedListener { view, hourOfDay, minute ->
            formatTime(hourOfDay, minute)
        }
    }

    private fun formatTime(hourOfDay: Int, minute: Int): String {
        val formattedHour = if (hourOfDay > 12) {
            "오후 ${hourOfDay - 12}"
        } else {
            "오전 $hourOfDay"
        }
        val formattedTime = "${formattedHour}시 ${minute}분"
        binding.txtSelectedTime.text = formattedTime
        return formattedTime
    }

    private fun formatWeddingDate(): String {
        val selectedYear = binding.dpWeddingDate.year
        val selectedMonth = binding.dpWeddingDate.month + 1
        val selectedDay = binding.dpWeddingDate.dayOfMonth
        val selectedHour = binding.tpWeddingTime.hour
        val selectedMinute = binding.tpWeddingTime.minute

        return String.format(
            Locale.getDefault(),
            "%04d-%02d-%02d %02d:%02d",
            selectedYear,
            selectedMonth,
            selectedDay,
            selectedHour,
            selectedMinute
        )
    }
}
