package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path

interface WeddingService {

    @GET("weddings/{weddingId}")
    fun getWeddingInfo(
        @Path("weddingId") weddingId: Int
    ): Call<WeddingInfoResponse>
}
