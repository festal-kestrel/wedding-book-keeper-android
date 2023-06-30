package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityCoupleMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class CoupleMyPageActivity : BaseActivity<ActivityCoupleMyPageBinding>(R.layout.activity_couple_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.btnWeddingInfo.setOnClickListener {
            var intent = Intent(this, WeddingDatePickerActivity::class.java)
            startActivity(intent)
        }

        binding.btnQr.setOnClickListener {
            var intent = Intent(this, GuestEntryQRActivity::class.java)
            startActivity(intent)
        }

        binding.btnAdminCode.setOnClickListener {
            var intent = Intent(this, AdminCodeActivity::class.java)
            startActivity(intent)
        }

        binding.btnPartnerRegister.setOnClickListener {
        }

        fun onClick(habitId: Long) {
        }

        binding.btnToolbarSeeMore.setOnClickListener {
            ChangeRoleFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, ChangeRoleFragment.TAG)
        }
    }
}
