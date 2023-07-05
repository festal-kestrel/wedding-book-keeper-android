package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingQrResponse
import com.example.wedding_book_keeper.databinding.ActivityGuestEntryQractivityBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestEntryQRActivity : BaseActivity<ActivityGuestEntryQractivityBinding>(R.layout.activity_guest_entry_qractivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            finish()
        }
        getWeddingQr(90)

        binding.textSaveQrBtn.setOnClickListener {
            saveImageToGallery()
        }
    }

    private fun getWeddingQr(weddingId: Int) {
        WeddingBookKeeperClient.weddingService.getWeddingQr(WeddingBookKeeperApplication.prefs.weddingId).enqueue(object : Callback<WeddingQrResponse> {
            override fun onResponse(call: Call<WeddingQrResponse>, response: Response<WeddingQrResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("qrImgUrl", it.qrImgUrl)
                        editor.apply()
                        Log.d("hong", "onResponse: ${response.body()}")

                        updateUI()
                    }
                }
            }

            override fun onFailure(call: Call<WeddingQrResponse>, t: Throwable) {
                Log.e("getWeddingQr", "Error: ${t.message}")
            }
        })
    }

    private fun updateUI() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val qrImgUrl = sharedPref.getString("qrImgUrl", null)

        Glide.with(this)
            .load(qrImgUrl)
            .into(binding.imgQr)
    }

    private fun saveImageToGallery() {
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val qrImgUrl = sharedPref.getString("qrImgUrl", null)

        Glide.with(this)
            .asBitmap()
            .load(qrImgUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val resolver = contentResolver
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, "QR Image.jpg")
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/")
                    }

                    val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    uri?.let {
                        resolver.openOutputStream(it)?.use { outputStream ->
                            if (!resource.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                                Toast.makeText(this@GuestEntryQRActivity, "QR코드를 갤러리에 저장했습니다.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@GuestEntryQRActivity, "QR코드 저장에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // This method is intentionally empty
                }
            })
    }
}
