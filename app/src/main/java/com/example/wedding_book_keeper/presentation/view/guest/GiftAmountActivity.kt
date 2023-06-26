package com.example.wedding_book_keeper.presentation.view.guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGiftAmountBinding
import com.example.wedding_book_keeper.databinding.ActivityLoginBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GiftAmountActivity : BaseActivity<ActivityGiftAmountBinding>(R.layout.activity_gift_amount) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_amount)
    }
}
