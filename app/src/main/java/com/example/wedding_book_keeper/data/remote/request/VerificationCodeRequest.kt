package com.example.wedding_book_keeper.data.remote.request

class VerificationCodeRequest {
    var verificationCode: String? = null

    constructor(verificationCode: String) {
        this.verificationCode = verificationCode
    }
}
