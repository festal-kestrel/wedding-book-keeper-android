package com.example.wedding_book_keeper

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class WeddingBookKeeperApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        // KaKao SDK  초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
