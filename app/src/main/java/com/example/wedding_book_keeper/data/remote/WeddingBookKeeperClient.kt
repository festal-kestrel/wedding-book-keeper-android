package com.example.wedding_book_keeper.data.remote

import com.example.wedding_book_keeper.data.remote.api.WeddingService
import com.example.wedding_book_keeper.data.remote.api.AuthService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeddingBookKeeperClient {

    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    val weddingService: WeddingService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideApiRetrofit(WeddingService::class.java)
    }

    val authService: AuthService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideApiRetrofit(AuthService::class.java)
    }

    private fun <T> provideApiRetrofit(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpLoggingClient())
        .build()
        .create(clazz)

    private fun provideHttpLoggingClient(): OkHttpClient =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build() }
}
