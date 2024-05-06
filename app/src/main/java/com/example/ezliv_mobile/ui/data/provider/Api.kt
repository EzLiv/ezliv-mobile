package com.example.ezliv_mobile.ui.data.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    const val BASE_URL = "http://34.232.238.8/api/"

    private var authToken: String? = null

    fun setAuthToken(token: String) {
        authToken = token
    }

    fun getInstance() : Retrofit {
        val httpClient = OkHttpClient.Builder()

        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            authToken?.let {
                requestBuilder.header("Authorization", "Bearer $it")
            }
            val request = requestBuilder.method(original.method, original.body).build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(interceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}