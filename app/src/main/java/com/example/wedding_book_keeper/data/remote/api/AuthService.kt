package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.request.VerificationCodeRequest
import com.example.wedding_book_keeper.data.remote.response.ManagerVerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.VerifyManagerVerificationCodeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @GET("auth/verification-code")
    fun getPartnerVerificationCode(
    ): Call<VerificationCodeResponse>

    @POST("auth/verification-code")
    fun verifyPartnerVerificationCode(
        @Body request: VerificationCodeRequest
    ): Call<Unit>

    @POST("auth/verification-code/admin")
    fun verifyManagerVerificationCode(
        @Body request: VerificationCodeRequest
    ): Call<VerifyManagerVerificationCodeResponse>
}
