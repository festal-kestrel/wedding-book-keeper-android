package com.example.wedding_book_keeper.domain.repository

import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode

interface AuthRepository {

    suspend fun getPartnerVerificationCode(): Result<VerificationCode>
}
