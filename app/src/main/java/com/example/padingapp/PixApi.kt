package com.example.padingapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "32118014-4d34477c3db32855401b2e32e",
        @Query("q")q: String,
        @Query("page")page:Int = 1,
        @Query("per_page") per_page:Int = 20
    ):Call<PixModel>
}