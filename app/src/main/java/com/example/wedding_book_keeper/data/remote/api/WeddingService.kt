package com.example.wedding_book_keeper.data.remote.api

import com.example.wedding_book_keeper.data.remote.response.TestResponse
import retrofit2.Call;
import retrofit2.http.GET;

interface WeddingService {

    @GET("weddings")
    fun getTestApi(): Call<TestResponse>

}
