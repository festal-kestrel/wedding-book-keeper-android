package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.request.VerificationCodeRequest
import com.example.wedding_book_keeper.data.remote.response.VerificationCodeResponse
import com.example.wedding_book_keeper.databinding.ActivityPartnerConnectBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.VerificationCodeDialogFragment.Companion.TAG
import com.example.wedding_book_keeper.presentation.view.wedding.schedule.ScheduleActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartnerConnectActivity : BaseActivity<ActivityPartnerConnectBinding>(R.layout.activity_partner_connect) {
    private var txtCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
        copyCode()
    }

    private fun initView() {
        WeddingBookKeeperClient.authService.getPartnerVerificationCode().enqueue(object : Callback<VerificationCodeResponse> {
            override fun onResponse(
                call: Call<VerificationCodeResponse>,
                response: Response<VerificationCodeResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    binding.txtCode.text = response.body()?.verificationCode
                    return;
                }
                showToast("유효하지 않거나 만료된 인증 코드입니다.")
            }

            override fun onFailure(call: Call<VerificationCodeResponse>, t: Throwable) {
                showToast("실패")
            }
        })
    }

    private fun copyCode() {
        binding.txtCode.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val adminCode = binding.txtCode.text.toString()
            val clipData = ClipData.newPlainText("admin_code", adminCode)
            clipboardManager.setPrimaryClip(clipData)

            showToast("복사 완료")
        }
        binding.txtCodeDesc.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val adminCode = binding.txtCode.text.toString()
            val clipData = ClipData.newPlainText("admin_code", adminCode)
            clipboardManager.setPrimaryClip(clipData)

            showToast("복사 완료")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initEvent() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            Log.d("txtCode","partnerConnectAct : "+binding.txtCode.text)
            intent.putExtra("txtCode", binding.txtCode.text)

            startActivity(intent)
        }
        binding.btnGoBack.setOnClickListener{
            finish()
        }
        binding.txtEnterMainPage.setOnClickListener{
            val intent = Intent(this, CoupleMainActivity::class.java)
            startActivity(intent)
        }
        binding.btnPartnerRegister.setOnClickListener {
            val dialogFragment = VerificationCodeDialogFragment.newInstance()
            dialogFragment.setOnVerificationCodeEnteredListener(object :
                VerificationCodeDialogFragment.OnVerificationCodeEnteredListener {
                override fun onVerificationCodeEntered(verificationCode: String) {
                    WeddingBookKeeperClient.authService.verifyPartnerVerificationCode(
                        VerificationCodeRequest(verificationCode)
                    ).enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                val intent = Intent(this@PartnerConnectActivity, CoupleMainActivity::class.java)
                                startActivity(intent)
                                return;
                            }
                            showToast("실패")
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            showToast("실패")
                        }
                    })
                }
            })
            dialogFragment.show(supportFragmentManager, TAG)
        }
    }
}
