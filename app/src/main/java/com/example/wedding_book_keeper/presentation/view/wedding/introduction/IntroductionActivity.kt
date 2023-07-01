package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity
import com.example.wedding_book_keeper.presentation.view.mypage.GuestEntryQRActivity


class IntroductionActivity : BaseActivity<ActivityIntroductionBinding>(R.layout.activity_introduction) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.btnNext.setOnClickListener {
            var intent = Intent(this, CoupleMainActivity::class.java)
            startActivity(intent)
        }
    }
}
