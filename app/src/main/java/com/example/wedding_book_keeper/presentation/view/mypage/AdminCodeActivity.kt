package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.ManagerVerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.WeddingManagerCodeResponse
import com.example.wedding_book_keeper.databinding.ActivityAdminCodeBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.example.wedding_book_keeper.presentation.view.donation.manager.ManagerMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminCodeActivity : BaseActivity<ActivityAdminCodeBinding>(R.layout.activity_admin_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView();
        initEvent();
        getWeddingQr(90)
    }

    private fun initEvent() {
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
    }

    private fun initView() {
        getManagerVerificationCode()
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
                    }
                }
            }

            override fun onFailure(call: Call<WeddingManagerCodeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getManagerVerificationCode() {
        WeddingBookKeeperClient.weddingService.getManagerVerificationCode(3)
            .enqueue(object : Callback<ManagerVerificationCodeResponse> {
            override fun onResponse(
                call: Call<ManagerVerificationCodeResponse>,
                response: Response<ManagerVerificationCodeResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    binding.txtAdminCode.text = response.body()?.weddingId.toString()
                    WeddingBookKeeperApplication.prefs.weddingId=response.body()?.weddingId
                    val intent = Intent(this@AdminCodeActivity, ManagerMainActivity::class.java)
                    startActivity(intent)
                    return;
                }
            }
            override fun onFailure(call: Call<ManagerVerificationCodeResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val managerCode = sharedPref.getString("managerCode", binding.txtAdminCode.text.toString())

        this.binding.txtAdminCode.text = managerCode
    }
}
