package com.example.wedding_book_keeper.presentation.view.wedding.schedule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityScheduleBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.introduction.IntroductionActivity


class ScheduleActivity : BaseActivity<ActivityScheduleBinding>(R.layout.activity_schedule) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.txtSelectedDate.text = "${binding.dpWeddingDate.year}년 ${binding.dpWeddingDate.month + 1}월 ${binding.dpWeddingDate.dayOfMonth}일"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, IntroductionActivity::class.java);
            startActivity(intent)
        }
        binding.dpWeddingDate.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            binding.txtSelectedDate.text = "${year}년 ${monthOfYear + 1}월 ${dayOfMonth}일"
        }
    }
}