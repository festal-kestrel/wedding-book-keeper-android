package com.example.wedding_book_keeper.data.remote.response

data class GuestDonationReceiptsResponse(
    val groomName: String? = "",
    val brideName: String? = "",
    val guests: List<GuestDonationReceiptResponse>
)
