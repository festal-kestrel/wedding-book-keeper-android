package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.databinding.ActivitySecondIntroBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity

class SecondIntroActivity : BaseActivity<ActivitySecondIntroBinding>(R.layout.activity_second_intro) {
    private val delayMillis: Long = 2000 // 5초
    private lateinit var txtCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtCode = intent.getStringExtra("txtCode").toString()
        Log.d("txtCode","세컨드화면, 받아온값 : "+txtCode)
        val intent = Intent(this, ThirdIntroActivity::class.java)
        intent.putExtra("txtCode", txtCode)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}
