package com.example.wedding_book_keeper.data.remote.datasource.auth

import com.example.wedding_book_keeper.data.remote.api.AuthService
import com.example.wedding_book_keeper.data.remote.response.auth.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.base.BaseResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
   private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun getPartnerVerificationCode(): Result<BaseResponse<VerificationCodeResponse>> {
        return authService.getPartnerVerificationCode();
    }
}
