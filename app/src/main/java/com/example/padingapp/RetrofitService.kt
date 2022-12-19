package com.example.padingapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    fun getClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(provideInterceptor())
            .build()
    }

    fun provideInterceptor(): HttpLoggingInterceptor{
        val logger= HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getApi() = retrofit.create(PixApi::class.java)
}