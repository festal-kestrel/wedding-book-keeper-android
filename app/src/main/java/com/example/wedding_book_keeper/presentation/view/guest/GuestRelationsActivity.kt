package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            var relationDesc = binding.editRelation.text.toString()
            val intent = Intent(this, GiftAmountActivity::class.java)
            intent.putExtra("guestSide", if (guestSide != "-1") guestSide.toInt() else -1)
            intent.putExtra("relationDesc", relationDesc)
            intent.putExtra("weddingId", weddingId)  // weddingId 추가
            Log.d("qr", "Provided_relationDesc: ${intent.getStringExtra("relationDesc")}")
            Log.d("qr", "Provided_guestSide: ${intent.getIntExtra("guestSide", -1)}")
            startActivity(intent)
        }
    }
}
