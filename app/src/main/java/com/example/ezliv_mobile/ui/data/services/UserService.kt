package com.example.ezliv_mobile.ui.data.services

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/authenticate")
    suspend fun login(@Body request: LoginRequest) : Response<Token>
}