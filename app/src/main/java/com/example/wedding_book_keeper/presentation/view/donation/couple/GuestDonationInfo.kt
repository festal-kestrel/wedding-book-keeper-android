package com.example.wedding_book_keeper.presentation.view.donation.couple

import java.time.LocalDate

class GuestDonationInfo(
    val side: String,
    val relation: String,
    val guestName: String,
    val amount: Int,
    val donationDate: String
) {

    val formattedAmount: String
        get() = String.format("%,d원", amount)
}
