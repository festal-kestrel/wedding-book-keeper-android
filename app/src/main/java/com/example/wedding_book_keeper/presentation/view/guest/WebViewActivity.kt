package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    var validationChk: Int = 0

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
        integrator.setPrompt("QR 코드를 사각형 안에 맞춰주세요")
        scanQRCode.launch(integrator.createScanIntent())
    }

    private fun parseQRInfo(info: String) {
        val infoParts = info.split(",")
        for (part in infoParts) {
            val keyValuePair = part.split(":").map { it.trim() }
            if (keyValuePair.size == 2) {
                val key = keyValuePair[0]
                val value = keyValuePair[1]
                when (key) {
                    "weddingId" -> {
                        weddingId = value.toInt()
                    }

                    "serviceName" -> {
                        validationChk = if (value.toString() != "weddingBookKeeper") -1 else 0
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
        val location = sharedPref.getString("location", null)

        if (memberName != null) {
            this.binding.txtGroomName.text = memberName
        }
        if (partnerName != null) {
            this.binding.txtBridalName.text = partnerName
        }
        if (weddingDate != null) {
            this.binding.weddingdate.text = weddingDate
        }
        if (location != null) {
            this.binding.txtLocation.text = location
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnScanQr.setOnClickListener {
            initiateScan()
        }
        binding.btnGoRelation.setOnClickListener {
            if (validationChk == 0) {
                val intent = Intent(this, GuestRelationsActivity::class.java)
                intent.putExtra("weddingId", weddingId)
                Log.d("qr", "Provided_weddingId: ${intent.getIntExtra("weddingId", 0)}")
                startActivity(intent)
            } else {
                // validationChk is not 0, so we don't move to the next activity.
                // Display a toast message to inform the user.
                Toast.makeText(this, "유효한 QR코드가 아닙니다.", Toast.LENGTH_LONG).show()
            }
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
                        editor.putString("location", it.location)
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
