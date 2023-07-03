package com.example.wedding_book_keeper.domain.repository

import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getPartnerVerificationCode(): Flow<Result<VerificationCode>>
}
