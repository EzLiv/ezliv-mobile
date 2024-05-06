package com.example.ezliv_mobile.ui.data.repositories

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.Token
import retrofit2.Response

interface IAuthRepository {
    suspend fun login(loginRequest: LoginRequest): Response<Token>
}