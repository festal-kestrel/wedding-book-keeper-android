package com.example.wedding_book_keeper.presentation.view.mypage

import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestMyPageActivity : BaseActivity<ActivityGuestMyPageBinding>(R.layout.activity_guest_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun onClick(habitId: Long) {
        }

        binding.btnToolbarSeeMore.setOnClickListener {
            ChangeRoleFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, ChangeRoleFragment.TAG)
        }
        binding.btnToolbarBack.setOnClickListener{
            finish()
        }
    }
}
