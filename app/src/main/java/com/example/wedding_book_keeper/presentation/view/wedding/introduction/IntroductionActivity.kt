package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.mypage.GuestEntryQRActivity
import com.example.wedding_book_keeper.presentation.view.mypage.MyPageActivity

class IntroductionActivity : BaseActivity<ActivityIntroductionBinding>(R.layout.activity_introduction) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.btnNext.setOnClickListener {
            var intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}
