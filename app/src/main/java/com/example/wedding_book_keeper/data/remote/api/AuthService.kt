package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.response.VerificationCodeResponse
import retrofit2.Call
import retrofit2.http.GET

interface AuthService {

    @GET("auth/verification-code")
    fun getPartnerVerificationCode(
    ): Call<VerificationCodeResponse>
}
