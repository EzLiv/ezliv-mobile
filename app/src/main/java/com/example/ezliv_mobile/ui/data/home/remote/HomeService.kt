package com.example.ezliv_mobile.ui.data.home.remote

import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("condominiums/towers/apartments/residents/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<UserModel>

    @GET("condominiums/{id}/notices")
    suspend fun getNotices(@Path("id") condominiumId: String): Response<List<NoticeModel>>
}