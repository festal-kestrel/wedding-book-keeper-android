package com.example.wedding_book_keeper.presentation.view.donation.couple

import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptResponse

data class GuestDonationInfo(
    val guestName: String = "",
    val guestSide: String = "",
    val relation: String = "",
    val hasPaid: Boolean,
    val donationAmount: Int,
    val weddingDate: String? = "",
    var isChecked: Boolean
) {

    val formattedAmount: String
        get() = String.format("%,d원", donationAmount)

    companion object {
        fun convertToGuestDonationInfo(guestDonationReceipt: GuestDonationReceiptResponse): GuestDonationInfo {

            return GuestDonationInfo(
                guestName = guestDonationReceipt.guestName,
                guestSide = guestDonationReceipt.guestSide,
                relation = guestDonationReceipt.relation,
                hasPaid = guestDonationReceipt.hasPaid,
                donationAmount = guestDonationReceipt.donationAmount,
                weddingDate = guestDonationReceipt.weddingDate ?: "",
                isChecked = guestDonationReceipt.isChecked
            )
        }
    }
}
