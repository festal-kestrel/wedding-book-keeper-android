package com.example.wedding_book_keeper.presentation.view.mypage

import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityAdminMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class AdminMyPageActivity : BaseActivity<ActivityAdminMyPageBinding>(R.layout.activity_admin_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun onClick(habitId: Long) {
        }

        binding.btnToolbarSeeMore.setOnClickListener {
            ChangeRoleFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, ChangeRoleFragment.TAG)
        }
        binding.btnToolbarBack.setOnClickListener {
            finish()
        }
    }
}
