package com.example.wedding_book_keeper.domain.usecase

import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode
import com.example.wedding_book_keeper.domain.repository.AuthRepository
import javax.inject.Inject

class GetPartnerVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): VerificationCode {
        return authRepository.getPartnerVerificationCode()
    }
}
