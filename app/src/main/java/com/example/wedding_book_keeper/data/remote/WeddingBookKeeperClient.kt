package com.example.wedding_book_keeper.data.remote

import com.example.wedding_book_keeper.data.remote.api.WeddingService
import com.example.wedding_book_keeper.presentation.config.AuthInterceptor
import com.example.wedding_book_keeper.presentation.view.guest.api.LoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeddingBookKeeperClient {

    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    val weddingService: WeddingService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideApiRetrofit(WeddingService::class.java)
    }

    val loginService: LoginService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideApiRetrofit(LoginService::class.java)
    }

    private fun <T> provideApiRetrofit(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(clazz)

}
