package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityPartnerConnectBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class PartnerConnectActivity : BaseActivity<ActivityPartnerConnectBinding>(R.layout.activity_partner_connect) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {
        binding.btnPartnerConnect.setOnClickListener {
            /**
             * 인증번호 다이얼로그 창
             */
        }
    }
}
