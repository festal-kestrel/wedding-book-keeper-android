package com.example.wedding_book_keeper.presentation.view.guest

import android.os.Bundle
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityViewQrcodeBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity

class ViewQrcodeActivity : BaseActivity<ActivityViewQrcodeBinding>(R.layout.activity_view_qrcode) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_qrcode)
    }
}

