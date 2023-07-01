package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityCoupleMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.PartnerConnectActivity

class CoupleMyPageActivity : BaseActivity<ActivityCoupleMyPageBinding>(R.layout.activity_couple_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.layoutRowWeddingInfo.setOnClickListener {
            var intent = Intent(this, WeddingDatePickerActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowQr.setOnClickListener {
            var intent = Intent(this, GuestEntryQRActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowAdminCode.setOnClickListener {
            var intent = Intent(this, AdminCodeActivity::class.java)
            startActivity(intent)
        }

        binding.layoutRowPartnerRegister.setOnClickListener {
            var intent = Intent(this, PartnerConnectActivity::class.java)
            startActivity(intent)
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
