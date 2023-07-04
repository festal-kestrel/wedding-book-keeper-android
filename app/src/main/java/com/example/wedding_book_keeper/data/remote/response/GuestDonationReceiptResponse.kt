package com.example.wedding_book_keeper.data.remote.response

data class GuestDonationReceiptResponse(
    val guestName: String = "",
    val guestSide: String = "",
    val relation: String = "",
    val hasPaid: Boolean,
    val donationAmount: Int,
    val weddingDate: String? = ""
)
