package com.example.wedding_book_keeper.data.remote.datasource.auth

import com.example.wedding_book_keeper.data.remote.response.auth.VerificationCodeResponse
import com.example.wedding_book_keeper.data.remote.response.base.BaseResponse

interface AuthRemoteDataSource {
    suspend fun getPartnerVerificationCode(): Result<VerificationCodeResponse>
}
