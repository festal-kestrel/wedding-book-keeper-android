package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.content.Intent
import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityPartnerConnectBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.schedule.ScheduleActivity

class PartnerConnectActivity : BaseActivity<ActivityPartnerConnectBinding>(R.layout.activity_partner_connect) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
        }
        binding.btnPartnerConnect.setOnClickListener {
            val dialogFragment = VerificationCodeDialogFragment.newInstance()
            dialogFragment.setOnVerificationCodeEnteredListener(object :
                    VerificationCodeDialogFragment.OnVerificationCodeEnteredListener {
                override fun onVerificationCodeEntered(verificationCode: String) {
                    /**
                     * 입력된 인증번호 처리 로직
                     */
                }
            })
            dialogFragment.show(supportFragmentManager, VerificationCodeDialogFragment.TAG)
        }
    }
}
