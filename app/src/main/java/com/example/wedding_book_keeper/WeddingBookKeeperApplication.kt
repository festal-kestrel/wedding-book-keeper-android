package com.example.wedding_book_keeper

import android.app.Application
import com.example.wedding_book_keeper.presentation.config.Prefs
import com.kakao.sdk.common.KakaoSdk

//@HiltAndroidApp
class WeddingBookKeeperApplication : Application() {
    companion object{
        lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs=Prefs(applicationContext)
        // KaKao SDK  초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
