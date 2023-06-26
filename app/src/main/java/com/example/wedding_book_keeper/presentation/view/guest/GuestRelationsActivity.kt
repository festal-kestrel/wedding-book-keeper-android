package com.example.wedding_book_keeper.presentation.view.guest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestRelationsBinding
import com.example.wedding_book_keeper.databinding.ActivityLoginBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestRelationsActivity :
    BaseActivity<ActivityGuestRelationsBinding>(R.layout.activity_guest_relations) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnGoGiftAmount.setOnClickListener {
            Log.d("hong","버튼 눌림")
            val intent = Intent(this, GiftAmountActivity::class.java)
            startActivity(intent)
        }
    }
}
