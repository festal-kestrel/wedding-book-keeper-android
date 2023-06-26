package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityMainBinding
import com.example.wedding_book_keeper.databinding.ActivityMyPageBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class MyPageActivity : BaseActivity<ActivityMyPageBinding>(R.layout.activity_my_page) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.btnToolbarBack.setOnClickListener {
            finish()
        }

        binding.btnWeddingInfo.setOnClickListener {
            var intent = Intent(this, WeddingDatePickerActivity::class.java)
            startActivity(intent)
        }

        binding.btnQr.setOnClickListener {
            Log.d("hong", "here")
            try{
                var intent = Intent(this, GuestEntryQRActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        binding.btnAdminCode.setOnClickListener {
            var intent = Intent(this, AdminCodeActivity::class.java)
            startActivity(intent)
        }

        binding.btnPartnerRegister.setOnClickListener {
            // Handle redirect logic
        }


        fun onClick(habitId: Long) {
            Log.d("hong", "")
//            val intent = habitUpdateNavigator.intent(requireContext())
//            intent.putExtra(HABIT_ID, habitId)
//            addResultLauncher.launch(intent)
        }
        binding.btnToolbarSeeMore.setOnClickListener {
            ChangeRoleFragment.newInstance(
                onClick = ::onClick
            ).show(supportFragmentManager, ChangeRoleFragment.TAG)
        }


    }
}
