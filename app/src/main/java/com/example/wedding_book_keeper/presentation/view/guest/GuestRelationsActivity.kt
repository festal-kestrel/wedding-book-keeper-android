package com.example.wedding_book_keeper.presentation.view.guest

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestRelationsBinding
import com.example.wedding_book_keeper.databinding.ActivityLoginBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestRelationsActivity : BaseActivity<ActivityGuestRelationsBinding>(R.layout.activity_guest_relations) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_relations)


    }
}
