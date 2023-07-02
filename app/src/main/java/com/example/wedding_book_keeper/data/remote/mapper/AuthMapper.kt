package com.example.wedding_book_keeper.data.remote.mapper

import com.example.wedding_book_keeper.data.remote.response.auth.VerificationCodeResponse
import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode

fun VerificationCodeResponse.toVerificationCode(): VerificationCode {
    return VerificationCode(
        verificationCode = verificationCode
    )
}
