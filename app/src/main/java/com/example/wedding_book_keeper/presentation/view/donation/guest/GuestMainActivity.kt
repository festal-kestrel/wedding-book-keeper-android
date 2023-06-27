package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class GuestMainActivity : BaseActivity<ActivityGuestMainBinding>(R.layout.activity_guest_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            /*
            * 마이페이지 화면 호출
            */
        }

        binding.btnQrcode.setOnClickListener {
            /*
             * QR 인식 화면 호출
             */
        }


        initView()
    }

    private fun initView() {
        val weddingList = mutableListOf<GuestWeddingInfo>()

        binding.rvWeddingList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWeddingList.setHasFixedSize(true)
        binding.rvWeddingList.adapter = GuestMainWeddingAdapter(weddingList)

        // 샘플 데이터
        val newWeddings = mutableListOf(
            GuestWeddingInfo("홍길동", "김길순", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))),
            GuestWeddingInfo("홍길동", "김길순", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))),
            GuestWeddingInfo("홍길동", "김길순", 100000, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
        )

        GuestMainWeddingAdapter(weddingList).setItems(newWeddings)
    }
}
