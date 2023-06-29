package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGiftCompleteBinding
import com.example.wedding_book_keeper.databinding.ActivityGuestRelationsBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestMainActivity
import com.example.wedding_book_keeper.presentation.view.mypage.MyPageActivity

class GIftCompleteActivity : BaseActivity<ActivityGiftCompleteBinding>(R.layout.activity_gift_complete) {
    private val delayMillis: Long = 3000 // 3초

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_complete)

        val nextActivityIntent = Intent(this, GuestMainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(nextActivityIntent)
            finish()
        }, delayMillis)
    }
}
