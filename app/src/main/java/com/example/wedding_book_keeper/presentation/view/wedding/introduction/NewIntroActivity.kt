package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityNewIntroBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity

class NewIntroActivity : BaseActivity<ActivityNewIntroBinding>(R.layout.activity_new_intro) {
    private val delayMillis: Long = 3000 // 5초
    private lateinit var txtCode: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtCode = intent.getStringExtra("txtCode").toString()
        Log.d("txtCode","받은 값 : " + txtCode)
        val intent = Intent(this, SecondIntroActivity::class.java)
        intent.putExtra("txtCode", txtCode)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}
