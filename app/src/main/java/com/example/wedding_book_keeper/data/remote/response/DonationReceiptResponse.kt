package com.example.wedding_book_keeper.data.remote.response

data class DonationReceiptResponse(

    val groomName: String? = "",
    val brideName: String? = "",
    val donationAmount: Int,
    val weddingDate: String? = ""
)
