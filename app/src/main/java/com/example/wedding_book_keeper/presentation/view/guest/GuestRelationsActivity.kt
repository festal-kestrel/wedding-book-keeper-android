package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGuestRelationsBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class GuestRelationsActivity :
    BaseActivity<ActivityGuestRelationsBinding>(R.layout.activity_guest_relations) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val radioGroup = binding.radioGroup
        var guestSide = "-1"
        val weddingId = intent.getIntExtra("weddingId", 0)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == binding.radioBtnMale.id) {
                guestSide = "0"
            } else if (checkedId == binding.radioBtnFemale.id) {
                guestSide = "1"
            }
        }

        binding.btnGoGiftAmount.setOnClickListener {
            val relationDesc = binding.editRelation.text.toString()

            if(relationDesc.isEmpty() || guestSide == "-1") {
                Toast.makeText(this, "신랑,신부 측 선택과 관계를 작성해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, GiftAmountActivity::class.java)
                intent.putExtra("guestSide", if (guestSide != "-1") guestSide.toInt() else -1)
                intent.putExtra("relationDesc", relationDesc)
                intent.putExtra("weddingId", weddingId)  // weddingId 추가
                Log.d("qr", "Provided_relationDesc: ${intent.getStringExtra("relationDesc")}")
                Log.d("qr", "Provided_guestSide: ${intent.getIntExtra("guestSide", -1)}")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }
        }
        binding.btnGoBack.setOnClickListener{
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
        }

    }
}
