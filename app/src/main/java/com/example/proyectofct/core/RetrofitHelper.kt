package com.example.proyectofct.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://viewnextandroid4.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitMock(): Retrofit {
        return Retrofit.Builder().baseUrl("https://viewnextandroid4.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}