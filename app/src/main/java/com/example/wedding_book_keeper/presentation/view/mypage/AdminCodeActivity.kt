package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityAdminCodeBinding
import com.example.wedding_book_keeper.databinding.ActivityCoupleMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

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
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
