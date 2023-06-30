package com.example.wedding_book_keeper.presentation.view.donation.couple

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityCoupleMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CoupleMainActivity : BaseActivity<ActivityCoupleMainBinding>(R.layout.activity_couple_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            /*
            * 마이페이지 화면 호출
            */
        }

        initView()
    }

    private fun initView() {
        val guestList = mutableListOf<GuestDonationInfo>()

        binding.rvGuestListByCouple.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvGuestListByCouple.setHasFixedSize(true)
        binding.rvGuestListByCouple.adapter = CoupleMainDonationAdapter(guestList)

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

        CoupleMainDonationAdapter(guestList).setItems(newDonations)

        binding.switchAmount.setOnCheckedChangeListener { p0, isCheck ->
            /**
             * 스위치 금액 숨기기 이벤트 처리
             */
        }

        binding.btnSide.setOnClickListener {
            fun onClick(l: Long) {
            }
            FilterFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, FilterFragment.TAG)
        }
    }
}
