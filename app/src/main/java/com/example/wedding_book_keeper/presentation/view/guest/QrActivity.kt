package com.example.wedding_book_keeper.presentation.view.guest

import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityQrBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.DefaultDecoderFactory

class QrActivity : BaseActivity<ActivityQrBinding>(R.layout.activity_qr) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        disableLager()

        binding.viewBarcode.run {
            barcodeView.decoderFactory = DefaultDecoderFactory(listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39))
            initializeFromIntent(intent)
            decodeContinuous { result ->
                // QR 코드가 인식됐을 때 처리
                Log.d("QRActivity", "message=${result.text}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.viewBarcode.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.viewBarcode.pause()
    }

    private fun disableLager(){
        val viewFinder = binding.viewBarcode.viewFinder
        try{
            val scannerField = viewFinder.javaClass.getDeclaredField("SCANNER_ALPHA")
            scannerField.isAccessible = true
            scannerField.set(viewFinder, intArrayOf(1))

        }catch (e: Exception){

        }

    }
}
