package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.request.WeddingUpdateInformationRequest
import com.example.wedding_book_keeper.data.remote.response.DonationReceiptsResponse
import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptsResponse
import com.example.wedding_book_keeper.data.remote.response.Role
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.data.remote.response.WeddingManagerCodeResponse
import com.example.wedding_book_keeper.data.remote.response.WeddingQrResponse
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WeddingService {

    @GET("weddings/{weddingId}")
    fun getWeddingInfo(
        @Path("weddingId") weddingId: Int
    ): Call<WeddingInfoResponse>

    @GET("weddings/{weddingId}/qr")
    fun getWeddingQr(
        @Path("weddingId") weddingId: Int
    ): Call<WeddingQrResponse>

    @GET("weddings/{weddingId}/admin/code")
    fun getManagerCode(
        @Path("weddingId") weddingId: Int
    ): Call<WeddingManagerCodeResponse>

    @PATCH("weddings/{weddingId}")
    fun updateWeddingInfo(
        @Path("weddingId") weddingId: Int,
        @Body weddingUpdateInformationRequest: WeddingUpdateInformationRequest
    ): Call<Unit>

    @GET("weddings")
    fun getDonationList(): Call<DonationReceiptsResponse>

    @GET("weddings/{weddingId}/guests")
    fun getGuestList(
        @Path("weddingId") weddingId: Int,
        @Query("role") role: Role
    ): Call<GuestDonationReceiptsResponse>
}
