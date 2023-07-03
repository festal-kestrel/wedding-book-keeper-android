package com.example.wedding_book_keeper.domain.usecase

import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode
import com.example.wedding_book_keeper.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPartnerVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Result<VerificationCode>> {
        return authRepository.getPartnerVerificationCode()
    }
}
