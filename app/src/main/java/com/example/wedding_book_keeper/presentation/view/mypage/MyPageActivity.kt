package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityMainBinding
import com.example.wedding_book_keeper.databinding.ActivityMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class MyPageActivity : BaseActivity<ActivityMyPageBinding>(R.layout.activity_my_page) {

    private lateinit var item1Button: Button
    private lateinit var item2Button: Button
    private lateinit var item3Button: Button
    private lateinit var item4Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        item1Button = findViewById(R.id.item1ImageView)
        item2Button = findViewById(R.id.item2ImageView)
        item3Button = findViewById(R.id.item3ImageView)
        item4Button = findViewById(R.id.item4ImageView)

        item1Button.setOnClickListener {
            var intent = Intent(this, WeddingDatePickerActivity::class.java)
            startActivity(intent)
        }

        item2Button.setOnClickListener {
            var intent = Intent(this, GuestEntryQRActivity::class.java)
            startActivity(intent)
        }

        item3Button.setOnClickListener {
        }

        item4Button.setOnClickListener {
            // Handle redirect logic
        }
    }
}
