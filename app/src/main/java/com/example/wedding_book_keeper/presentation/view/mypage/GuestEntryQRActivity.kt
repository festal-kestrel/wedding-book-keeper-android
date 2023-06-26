package com.example.wedding_book_keeper.presentation.view.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestEntryQractivityBinding
import com.example.wedding_book_keeper.databinding.ActivityWeddingDatePickerBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestEntryQRActivity : BaseActivity<ActivityGuestEntryQractivityBinding>(R.layout.activity_guest_entry_qractivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.btnToolbarBack.setOnClickListener {
            finish()
        }
    }
}
