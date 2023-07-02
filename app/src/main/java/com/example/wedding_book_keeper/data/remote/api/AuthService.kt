package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.response.auth.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.base.BaseResponse
import retrofit2.http.GET

interface AuthService {

    @GET("auth/verification-code")
    suspend fun getPartnerVerificationCode(): Result<BaseResponse<VerificationCodeResponse>>
}
