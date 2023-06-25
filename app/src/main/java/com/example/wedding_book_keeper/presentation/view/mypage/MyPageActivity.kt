package com.example.wedding_book_keeper.presentation.view.mypage

import WeddingDatePickerFragment
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.presentation.view.mypage.fragment.ImageFragment
import com.example.wedding_book_keeper.presentation.view.mypage.fragment.TextFragment


class MyPageActivity : AppCompatActivity() {

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
            val datePickerFragment = WeddingDatePickerFragment()
//            datePickerFragment.show(supportFragmentManager, "datePicker")
//            val datePickerFragment = DatePickerFragment()
            supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, datePickerFragment)
            .addToBackStack(null)
            .commit()
        }

        item2Button.setOnClickListener {
            val imageFragment = ImageFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, imageFragment)
                .addToBackStack(null)
                .commit()
        }

        item3Button.setOnClickListener {
            val textFragment = TextFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, textFragment)
                .addToBackStack(null)
                .commit()
        }

        item4Button.setOnClickListener {
            // Handle redirect logic
        }
    }
}