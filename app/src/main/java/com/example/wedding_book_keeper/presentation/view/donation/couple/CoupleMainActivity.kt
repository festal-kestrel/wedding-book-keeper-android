package com.example.wedding_book_keeper.presentation.view.donation.couple

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityCoupleMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.config.setStatusBarTransparent
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CoupleMainActivity : BaseActivity<ActivityCoupleMainBinding>(R.layout.activity_couple_main) {
    private lateinit var adapter: CoupleMainDonationAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            val intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }

        initView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        val guestList = mutableListOf<GuestDonationInfo>()

        adapter = CoupleMainDonationAdapter(guestList)
        binding.rvGuestListByCouple.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvGuestListByCouple.setHasFixedSize(true)
        binding.rvGuestListByCouple.adapter = adapter

        // 샘플 데이터
        val newDonations = mutableListOf(
            GuestDonationInfo(
                "신부측",
                "친한친구",
                "김길순kks",
                50000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            ),
            GuestDonationInfo(
                "신랑측",
                "지나가던사람",
                "박보선bbs",
                70000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            ),
            GuestDonationInfo(
                "신랑측",
                "코사동기",
                "오혁진ohj",
                100000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            ),
            GuestDonationInfo(
                "신부측",
                "총무",
                "송민진smj",
                500000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            ),
            GuestDonationInfo(
                "신부측",
                "코사7기",
                "교수님msw",
                300000,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            )
        )

        adapter.setItems(newDonations)

        CoupleMainDonationAdapter(guestList).setItems(newDonations)

        binding.switchAmount.setOnCheckedChangeListener { p0, isChecked ->
            (binding.rvGuestListByCouple.adapter as CoupleMainDonationAdapter).isAmountHidden = isChecked
            (binding.rvGuestListByCouple.adapter as CoupleMainDonationAdapter).notifyDataSetChanged()
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                (binding.rvGuestListByCouple.adapter as CoupleMainDonationAdapter).filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        binding.btnSide.setOnClickListener {
            FilterFragment.newInstance(::applyFilter).show(supportFragmentManager, FilterFragment.TAG)
        }
    }
    private fun applyFilter(side: String?) {
        val query = binding.editText.text.toString()
        adapter.filter(query, side)
    }
}
