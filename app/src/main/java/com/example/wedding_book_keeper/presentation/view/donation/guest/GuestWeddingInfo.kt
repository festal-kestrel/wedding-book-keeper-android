package com.example.wedding_book_keeper.presentation.view.donation.guest

class GuestWeddingInfo(
    val groomName: String,
    val bridalName: String,
    val amount: Int,
    val donationDate: String
) {
    val formattedAmount: String
        get() = String.format("%,d원", amount)
}
