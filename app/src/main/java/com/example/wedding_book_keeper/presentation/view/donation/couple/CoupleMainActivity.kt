package com.example.wedding_book_keeper.presentation.view.donation.couple

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
import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptResponse
import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptsResponse
import com.example.wedding_book_keeper.data.remote.response.Role
import com.example.wedding_book_keeper.databinding.ActivityCoupleMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoupleMainActivity : BaseActivity<ActivityCoupleMainBinding>(R.layout.activity_couple_main) {
    private lateinit var adapter: CoupleMainDonationAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMypage.setOnClickListener {
            val intent = Intent(this, CoupleMyPageActivity::class.java)
            startActivity(intent)
        }

        getGuestList(90)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {

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
        this.adapter.filter(query, side)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getGuestList(weddingId: Int) {
        var guests = mutableListOf<GuestDonationReceiptResponse>()

        val guestList = mutableListOf<GuestDonationInfo>()
        adapter = CoupleMainDonationAdapter(guestList)

        // 리사이클러뷰에 레이아웃 매니저 연결
        binding.rvGuestListByCouple.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvGuestListByCouple.setHasFixedSize(true)
        binding.rvGuestListByCouple.adapter = adapter

        WeddingBookKeeperClient.weddingService.getGuestList(weddingId, role = Role.PARTNER)
            .enqueue(object : Callback<GuestDonationReceiptsResponse> {
                override fun onResponse(
                    call: Call<GuestDonationReceiptsResponse>,
                    response: Response<GuestDonationReceiptsResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            it.guests?.let { guestDonationReceipts ->
                                guests.addAll(guestDonationReceipts)
                                showGuestDonationList(guests)
                            }
                            Log.d("hong", "onResponse: ${guests}")
                            showToastMessage("성공")
                        }
                    }
                }

                private fun showGuestDonationList(guests: MutableList<GuestDonationReceiptResponse>) {
                    adapter.setItemsApi(guests);
                }

                override fun onFailure(call: Call<GuestDonationReceiptsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}
