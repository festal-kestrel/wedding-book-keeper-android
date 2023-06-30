package com.example.wedding_book_keeper.presentation

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.example.wedding_book_keeper.BuildConfig


class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // KaKao SDK  초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
