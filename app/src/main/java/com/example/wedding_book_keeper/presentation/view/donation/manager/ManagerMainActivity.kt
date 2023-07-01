package com.example.wedding_book_keeper.presentation.view.donation.manager

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityManagerMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.GuestDonationInfo
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class ManagerMainActivity :
    BaseActivity<ActivityManagerMainBinding>(R.layout.activity_manager_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            val intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }

        initView()
    }

    private fun initView() {
        var guestList = mutableListOf<GuestDonationInfo>()

        binding.rvGuestListByManager.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvGuestListByManager.setHasFixedSize(true)
        binding.rvGuestListByManager.adapter = ManagerMainDonationAdapter(guestList)

        // 샘플 데이터
        val newDonations = mutableListOf(
            GuestDonationInfo(
                "신부측",
                "친한친구",
                "김길순",
                100000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            ),
            GuestDonationInfo(
                "신부측",
                "친한친구",
                "김길순",
                100000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            )
        )

        ManagerMainDonationAdapter(guestList).setItems(newDonations)
    }

}
