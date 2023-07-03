package com.example.wedding_book_keeper.data.remote.datasource.auth

import android.util.Log
import com.example.wedding_book_keeper.data.remote.api.AuthService
import com.example.wedding_book_keeper.data.remote.response.auth.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.base.BaseResponse
import com.example.wedding_book_keeper.data.remote.response.base.getResult
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
   private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun getPartnerVerificationCode(): Result<VerificationCodeResponse> {
        val partnerVerificationCode = authService.getPartnerVerificationCode()
        Log.d("TAG", "getPartnerVerificationCode: $partnerVerificationCode")
        return authService.getPartnerVerificationCode().getResult()!!
    }
}
