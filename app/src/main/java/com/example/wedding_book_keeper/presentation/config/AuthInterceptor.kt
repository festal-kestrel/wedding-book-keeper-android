package com.example.wedding_book_keeper.presentation.config

import android.util.Log
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req =
            chain.request().newBuilder().addHeader("Authorization", "Bearer ${WeddingBookKeeperApplication.prefs.token ?: ""}").build()
        Log.d("LOGIN", "INTERCEPTOR ==== $req")
        return chain.proceed(req)
    }
}