package com.example.ezliv_mobile.ui.data.services

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.NewPassword
import com.example.ezliv_mobile.ui.data.models.NoticeModel
import com.example.ezliv_mobile.ui.data.models.Token
import com.example.ezliv_mobile.ui.data.models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @POST("users/authenticate")
    suspend fun login(@Body request: LoginRequest) : Response<Token>

    @PUT("users/first-login/{id}")
    suspend fun firstLogin(@Path("id") id: String, @Body newPassword: NewPassword): Response<Unit>

    @GET("condominiums/towers/apartments/residents/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<UserModel>

    @GET("condominiums/{id}/notices")
    suspend fun getNotices(@Path("id") condominiumId: String): Response<List<NoticeModel>>
}