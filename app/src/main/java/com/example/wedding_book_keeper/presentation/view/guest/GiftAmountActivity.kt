package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityGiftAmountBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class GiftAmountActivity :
    BaseActivity<ActivityGiftAmountBinding>(R.layout.activity_gift_amount) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            val editTextContent = editText.text.toString()
            if (editTextContent.isEmpty() || editTextContent == "원") {
                Toast.makeText(this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, GIftCompleteActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
