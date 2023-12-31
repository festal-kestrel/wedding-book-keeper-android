package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestMainActivity


class IntroductionActivity : BaseActivity<ActivityIntroductionBinding>(R.layout.activity_introduction) {
    private val delayMillis: Long = 5000 // 5초

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nextActivityIntent = Intent(this, CoupleMainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(nextActivityIntent)
            finish()
        }, delayMillis)
    }
}
