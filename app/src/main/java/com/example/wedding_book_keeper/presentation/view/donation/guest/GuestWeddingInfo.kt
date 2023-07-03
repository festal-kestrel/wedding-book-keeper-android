package com.example.wedding_book_keeper.presentation.view.donation.guest

import com.example.wedding_book_keeper.data.remote.response.DonationReceiptResponse

data class GuestWeddingInfo(
    val groomName: String? = "",
    val brideName: String? = "",
    val donationAmount: Int,
    val weddingDate: String? = ""
) {
    val formattedAmount: String
        get() = String.format("%,d원", donationAmount)

    companion object {
        fun convertToGuestWeddingInfo(donationReceipt: DonationReceiptResponse): GuestWeddingInfo {

            return GuestWeddingInfo(
                groomName = donationReceipt.groomName ?: "홍길동",
                brideName = donationReceipt.brideName ?: "김춘향",
                donationAmount = donationReceipt.donationAmount,
                weddingDate = donationReceipt.weddingDate ?: ""
            )
        }
    }
}
