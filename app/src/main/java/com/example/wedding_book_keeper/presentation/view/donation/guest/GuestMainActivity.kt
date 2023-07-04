package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.DonationReceiptResponse
import com.example.wedding_book_keeper.data.remote.response.DonationReceiptsResponse
import com.example.wedding_book_keeper.databinding.ActivityGuestMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.guest.WebViewActivity
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class GuestMainActivity : BaseActivity<ActivityGuestMainBinding>(R.layout.activity_guest_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
        getDonationList()
    }

    private fun initEvent() {

        binding.btnMypage.setOnClickListener {
            val intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }

        binding.btnQrcode.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

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

    private fun getDonationList() {
        var donations = mutableListOf<DonationReceiptResponse>()

        val weddingList = mutableListOf<GuestWeddingInfo>()
        val adapter = GuestMainWeddingAdapter(weddingList)

        // 리사이클러뷰에 레이아웃 매니저를 연결
        binding.rvWeddingList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWeddingList.setHasFixedSize(true)
        binding.rvWeddingList.adapter = adapter

        WeddingBookKeeperClient.weddingService.getDonationList().enqueue(object : Callback<DonationReceiptsResponse> {
            override fun onResponse(call: Call<DonationReceiptsResponse>, response: Response<DonationReceiptsResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        it.donations?.let { donationReceipts ->
                            donations.addAll(donationReceipts)
                            showDonationList(donations)
                        }

                        Log.d("hong", "onResponse: ${donations}")
                        showToastMessage("성공")
                    }
                }
            }

            private fun showDonationList(donations: MutableList<DonationReceiptResponse>) {
                adapter.setItemsApi(donations);
            }

            override fun onFailure(call: Call<DonationReceiptsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}
