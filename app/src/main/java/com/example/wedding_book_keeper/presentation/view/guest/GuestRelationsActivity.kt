package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestRelationsBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestRelationsActivity :
    BaseActivity<ActivityGuestRelationsBinding>(R.layout.activity_guest_relations) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnGoGiftAmount.setOnClickListener {
            val intent = Intent(this, GiftAmountActivity::class.java)
            startActivity(intent)

        }

    }
}
