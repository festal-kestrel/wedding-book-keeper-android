package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.WeddingManagerCodeResponse
import com.example.wedding_book_keeper.databinding.ActivityAdminCodeBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminCodeActivity : BaseActivity<ActivityAdminCodeBinding>(R.layout.activity_admin_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.btnCopyCode.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val adminCode = binding.txtAdminCode.text.toString()
            val clipData = ClipData.newPlainText("admin_code", adminCode)
            clipboardManager.setPrimaryClip(clipData)

            showToast("복사 완료")
        }
        getWeddingQr(90)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getWeddingQr(weddingId: Int) {
        WeddingBookKeeperClient.weddingService.getManagerCode(weddingId).enqueue(object : Callback<WeddingManagerCodeResponse> {
            override fun onResponse(call: Call<WeddingManagerCodeResponse>, response: Response<WeddingManagerCodeResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("managerCode", it.managerCode)
                        editor.apply()
                        Log.d("hong", "onResponse: ${response.body()}")

                        updateUI()
                    }
                }
            }

            override fun onFailure(call: Call<WeddingManagerCodeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getManagerVerificationCode() {
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

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val managerCode = sharedPref.getString("managerCode", null)

        this.binding.txtAdminCode.text = managerCode
    }
}
