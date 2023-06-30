package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityWebViewBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.zxing.integration.android.IntentIntegrator

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {

    private val scanQRCode = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(result.resultCode, result.data)
            if (scanResult != null) {
                if (scanResult.contents == null) {
                    Log.d("hong", "cancelled")
                } else {
                    val info = scanResult.contents
                    Log.d("hong", "info=$info")
                    parseQRInfo(info)
                    updateUI()
                }
            }
        }
    }

    private fun initiateScan() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.setBeepEnabled(false)
        integrator.setPrompt("QR코드를 사각형 안에 맞춰주세요")
        scanQRCode.launch(integrator.createScanIntent())
    }

    private fun parseQRInfo(info: String) {
        Log.d("hong","parseQRInfo로 넘어온 값 확인 :"+info)
        val infoParts = info.split("\n")
        Log.d("hong", infoParts.toString())

        var memberName = ""
        var partnerName = ""
        var weddingDate = ""

        for (part in infoParts) {
            val keyValuePair = part.split(":").map { it.trim() }
            if (keyValuePair.size == 2) {
                val key = keyValuePair[0]
                val value = keyValuePair[1]
                when (key) {
                    "memberName" -> {
                        memberName = value
                        Log.d("hong", "memberName: $memberName")
                    }
                    "partnerName" -> {
                        partnerName = value
                        Log.d("hong", "partnerName: $partnerName")
                    }
                    "weddingDate" -> {
                        weddingDate = value
                        Log.d("hong", "weddingDate: $weddingDate")
                    }
                }
            }
        }

        Log.d("hong","변수에 저장된 값 확인 : "+memberName + partnerName + weddingDate)
        if (memberName.isNotEmpty() && partnerName.isNotEmpty() && weddingDate.isNotEmpty()) {
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("memberName", memberName)
            editor.putString("partnerName", partnerName)
            editor.putString("weddingDate", weddingDate)
            editor.apply()
            Log.d("hong","값은 받아옴"+memberName+partnerName+weddingDate)
        } else {
            Log.e("QRCodeError", "QR code did not contain all necessary information")
        }
    }

    private fun updateUI() {
        // Get the values from SharedPreferences
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val memberName = sharedPref.getString("memberName", null)
        val partnerName = sharedPref.getString("partnerName", null)
        val weddingDate = sharedPref.getString("weddingDate", null)
        Log.d("hong","함수호출은 됨")

        // Update the UI
        if (memberName != null) {
            this.binding.txtGroomName.text = memberName
        }

        if (partnerName != null) {
            this.binding.txtBridalName.text = partnerName
        }

        if (weddingDate != null) {
            Log.d("hong","정상적으로 값 받아옴")
            this.binding.weddingdate.text = weddingDate
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnScanQr.setOnClickListener {
            initiateScan()
        }
        binding.btnGoRelation.setOnClickListener{
            val intent = Intent(this, GuestRelationsActivity::class.java)
            startActivity(intent)
        }

        initiateScan()
    }
}
