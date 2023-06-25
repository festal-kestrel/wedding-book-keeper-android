package com.example.wedding_book_keeper.presentation.view.wedding.schedule

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityScheduleBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

@RequiresApi(Build.VERSION_CODES.O)
class ScheduleActivity : BaseActivity<ActivityScheduleBinding>(R.layout.activity_schedule) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.txtSelectedDate.text = "${binding.dpWeddingDate.year}년 ${binding.dpWeddingDate.month + 1}월 ${binding.dpWeddingDate.dayOfMonth}일"
    }

    private fun initEvent() {
        binding.dpWeddingDate.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            binding.txtSelectedDate.text = "${year}년 ${monthOfYear + 1}월 ${dayOfMonth}일"
        }
    }
}
