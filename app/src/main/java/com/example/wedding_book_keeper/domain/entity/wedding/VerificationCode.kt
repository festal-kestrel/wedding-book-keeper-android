package com.example.wedding_book_keeper.domain.entity.wedding

data class VerificationCode(
    val verificationCode: String
) {
    companion object {
        fun empty() = VerificationCode(
            verificationCode = ""
        )
    }
}
