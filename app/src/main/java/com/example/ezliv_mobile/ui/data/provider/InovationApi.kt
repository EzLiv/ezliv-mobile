package com.example.ezliv_mobile.ui.data.provider

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InovationApi {
    const val BASE_URL = "http://localhost:8082"

    fun getInstance() : Retrofit {
        val httpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}