package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.guest.GuestRelationsActivity
import com.example.wedding_book_keeper.presentation.view.guest.ViewQrcodeActivity
import com.example.wedding_book_keeper.presentation.view.guest.WebViewActivity
import com.example.wedding_book_keeper.presentation.view.mypage.MyPageActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class GuestMainActivity : BaseActivity<ActivityGuestMainBinding>(R.layout.activity_guest_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        binding.btnQrcode.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
        initView()

        binding.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                (binding.rvWeddingList.adapter as GuestMainWeddingAdapter).filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun initView() {
        val weddingList = mutableListOf<GuestWeddingInfo>()

        val adapter = GuestMainWeddingAdapter(weddingList)
        binding.rvWeddingList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWeddingList.setHasFixedSize(true)
        binding.rvWeddingList.adapter = adapter

        // 샘플 데이터
        val newWeddings = mutableListOf(
            GuestWeddingInfo("홍길동", "ssid", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))),
            GuestWeddingInfo("jinwook", "김길순", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))),
            GuestWeddingInfo("서명현", "bbs", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
        )

        adapter.setItems(newWeddings)
    }

}
