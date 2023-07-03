package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.POST
import retrofit2.http.Path

interface WeddingService {

    @GET("weddings/{weddingId}")
    fun getWeddingInfo(
        @Path("weddingId") weddingId: Int
    ): Call<WeddingInfoResponse>

    data class MemberWeddingInfo(
        val weddingId: Int,
        val donationAmount: Int,
        val relation: String,
        val isGroomSide: Int,
    )
    @POST("weddings/{weddingId}/guests/new")
    fun postMemberWeddingInfo(@Path("weddingId") weddingId: Int, @Body info: MemberWeddingInfo): Call<Void>
}
