package com.example.wedding_book_keeper.data.remote.response

import java.time.LocalDateTime

data class WeddingInfoResponse(

    val groomName: String = "",
    val brideName: String = "",
    val weddingDate: String? = "",
    val location: String = "",
)