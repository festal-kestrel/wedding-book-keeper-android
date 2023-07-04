package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.databinding.ActivitySecondIntroBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity

class SecondIntroActivity : BaseActivity<ActivitySecondIntroBinding>(R.layout.activity_second_intro) {
    private val delayMillis: Long = 2000 // 5초

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nextActivityIntent = Intent(this, ThirdIntroActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(nextActivityIntent)
            finish()
        }, delayMillis)
    }
}
