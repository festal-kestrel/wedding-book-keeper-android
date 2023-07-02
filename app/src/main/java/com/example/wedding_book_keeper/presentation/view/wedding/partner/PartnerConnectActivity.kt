package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityPartnerConnectBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.wedding.schedule.ScheduleActivity

class PartnerConnectActivity : BaseActivity<ActivityPartnerConnectBinding>(R.layout.activity_partner_connect) {

    private lateinit var viewModel: PartnerConnectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PartnerConnectViewModel::class.java)

        initData();
        initEvent()
    }

    private fun initData() {
        viewModel.verificationCode
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
        binding.txtVerifictionCode.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val adminCode = binding.txtVerifictionCode.text.toString()
            val clipData = ClipData.newPlainText("admin_code", adminCode)
            clipboardManager.setPrimaryClip(clipData)

            showToast("복사 완료")
        }
    }
}
