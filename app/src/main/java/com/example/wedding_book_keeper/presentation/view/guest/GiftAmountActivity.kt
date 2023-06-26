package com.example.wedding_book_keeper.presentation.view.guest

import com.example.wedding_book_keeper.R
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import com.example.wedding_book_keeper.databinding.ActivityGiftAmountBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


class GiftAmountActivity :
    BaseActivity<ActivityGiftAmountBinding>(com.example.wedding_book_keeper.R.layout.activity_gift_amount) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val editText = findViewById<EditText>(com.example.wedding_book_keeper.R.id.edit_gift)
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                editText.removeTextChangedListener(this)
                try {
                    var originalString = s.toString()

                    // Check if '원' is in the string
                    if (originalString.contains("원")) {
                        originalString = originalString.replace("원", "")
                    }

                    // Remove previous formatting character
                    val cleanString = originalString.replace(",", "")

                    // Parse cleanString as long value
                    val parsed = cleanString.toLong()

                    // Format as currency
                    val formatter: DecimalFormat =
                        NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat
                    formatter.applyPattern("#,###")
                    var formatted: String = formatter.format(parsed)

                    // Append '원' unit
                    formatted += "원"

                    // Set text
                    editText.setText(formatted)

                    // Set selection at the end of text
                    editText.setSelection(editText.text.length - 1)
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
            val intent = Intent(this, GIftCompleteActivity::class.java)
            startActivity(intent)

        }


    }

}
