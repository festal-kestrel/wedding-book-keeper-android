package com.example.wedding_book_keeper.presentation.view.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityWeddingDatePickerBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class WeddingDatePickerActivity : BaseActivity<ActivityWeddingDatePickerBinding>(R.layout.activity_wedding_date_picker) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }
    }

}
