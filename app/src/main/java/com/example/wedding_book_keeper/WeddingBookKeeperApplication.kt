package com.example.wedding_book_keeper

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class WeddingBookKeeperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        FirebaseMessaging.getInstance().isAutoInitEnabled = true

//        val TAG = "notification"
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
////            val msg = getString(R.string.msg_token_fmt, token)
//            Log.d(TAG, token)
//            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
//        })

        // Sets up permissions request launcher.

//            refreshUI()



        // KaKao SDK  초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }


}
