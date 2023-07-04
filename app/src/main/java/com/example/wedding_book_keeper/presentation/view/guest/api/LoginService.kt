package com.example.wedding_book_keeper.presentation.view.guest.api

import com.example.wedding_book_keeper.presentation.view.guest.dto.TokenResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("oauth/authorize")
    fun authorize(@Query("token") token: String): Call<TokenResponse>
}
