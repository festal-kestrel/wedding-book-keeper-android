package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.api.WeddingService
import com.example.wedding_book_keeper.databinding.ActivityGiftAmountBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class GiftAmountActivity :
    BaseActivity<ActivityGiftAmountBinding>(R.layout.activity_gift_amount) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = getIntent() // 현재 액티비티의 인텐트를 가져옵니다

        val guestSide = intent.getIntExtra("guestSide", 0)
        val relationDesc = intent.getStringExtra("relationDesc")
        val weddingId = intent.getIntExtra("weddingId", 0)

        val editText = binding.editGift
        editText.setInputType(InputType.TYPE_CLASS_NUMBER)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                editText.removeTextChangedListener(this)
                try {
                    var originalString = s.toString()

                    if (originalString.contains("원")) {
                        originalString = originalString.replace("원", "")
                    }

                    val cleanString = originalString.replace(",", "")
                    if (TextUtils.isEmpty(cleanString)) {
                        editText.setText("")
                    } else {
                        val parsed = cleanString.toLong()
                        val formatter: DecimalFormat =
                            NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat
                        formatter.applyPattern("#,###")
                        var formatted: String = formatter.format(parsed)
                        formatted += "원"
                        editText.setText(formatted)
                        editText.setSelection(editText.text.length - 1)
                    }
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                editText.addTextChangedListener(this)
            }
        })

        binding.btnPrevPage.setOnClickListener {
            val intent = Intent(this, GuestRelationsActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoGiftComplete.setOnClickListener {
            var donationAmount: Int
            val editGiftAmount = editText.text.toString()
            if (editGiftAmount.isEmpty() || editGiftAmount == "원") {
                Toast.makeText(this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, GIftCompleteActivity::class.java)
                startActivity(intent)
            }
            try {
                donationAmount = editGiftAmount.replace(",", "").replace("원", "").toInt()
            } catch (e: NumberFormatException) {
                donationAmount = editGiftAmount.replace("원", "").toInt()
            }
            postMemberWeddingInfo(weddingId, donationAmount, relationDesc.toString(), guestSide)
        }
    }

    private fun postMemberWeddingInfo(weddingId: Int, donationAmount: Int, relation: String, isGroomSide: Int) {
        val info = WeddingService.MemberWeddingInfo(
            weddingId = weddingId,
            donationAmount = donationAmount,
            relation = relation,
            isGroomSide = isGroomSide
        )

        WeddingBookKeeperClient.weddingService.postMemberWeddingInfo(weddingId, info).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("qr", "Success")
                } else {
                    Log.d("qr", "Failure: ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("qr", "Error: ${t.message}")
            }
        })
    }
}
