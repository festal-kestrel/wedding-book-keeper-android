package com.example.wedding_book_keeper.presentation.view.wedding.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityIntroductionBinding
import com.example.wedding_book_keeper.databinding.ActivityThirdIntroBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.guest.WebViewActivity
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity

class ThirdIntroActivity : BaseActivity<ActivityThirdIntroBinding>(R.layout.activity_third_intro) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnGoCoupleMyPage.setOnClickListener{
            val intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }
    }
}
