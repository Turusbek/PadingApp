package com.example.padingapp

import android.app.Application

class App: Application() {
    companion object{
        lateinit var api: PixApi
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit  = RetrofitService()
        api = retrofit.getApi()
    }
}