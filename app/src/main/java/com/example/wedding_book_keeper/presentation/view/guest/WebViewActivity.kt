package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.databinding.ActivityWebViewBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    var weddingId: Int = 0
    private val scanQRCode = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(result.resultCode, result.data)
            if (scanResult != null) {
                if (scanResult.contents == null) {
                    Log.d("qr", "cancelled")
                } else {
                    val info = scanResult.contents
                    parseQRInfo(info)
                    getWeddingInfo(weddingId)
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
        val infoParts = info.split("\n")
        for (part in infoParts) {
            val keyValuePair = part.split(":").map { it.trim() }
            if (keyValuePair.size == 2) {
                val key = keyValuePair[0]
                val value = keyValuePair[1]
                when (key) {
                    "weddingId" -> {
                        weddingId = value.replace("\"", "").toInt()
                    }
                }
            }
        }
    }

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val memberName = sharedPref.getString("memberName", null)
        val partnerName = sharedPref.getString("partnerName", null)
        val weddingDate = sharedPref.getString("weddingDate", null)

        if (memberName != null) {
            this.binding.txtGroomName.text = memberName
        }
        if (partnerName != null) {
            this.binding.txtBridalName.text = partnerName
        }
        if (weddingDate != null) {
            this.binding.weddingdate.text = weddingDate
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnScanQr.setOnClickListener {
            initiateScan()
        }
        binding.btnGoRelation.setOnClickListener {
            val intent = Intent(this, GuestRelationsActivity::class.java)
            intent.putExtra("weddingId", weddingId)
            Log.d("qr", "Provided_weddingId: ${intent.getIntExtra("weddingId", 0)}")
            startActivity(intent)
        }

        initiateScan()
    }

    private fun getWeddingInfo(weddingId: Int) {
        WeddingBookKeeperClient.weddingService.getWeddingInfo(weddingId).enqueue(object : Callback<WeddingInfoResponse> {
            override fun onResponse(call: Call<WeddingInfoResponse>, response: Response<WeddingInfoResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("memberName", it.groomName)
                        editor.putString("partnerName", it.brideName)
                        editor.putString("weddingDate", it.weddingDate?.let { it1 -> formatDate(it1) })
                        editor.apply()
                        updateUI()
                    }
                }
            }

            override fun onFailure(call: Call<WeddingInfoResponse>, t: Throwable) {
                // handle error
            }
        })
    }

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}
