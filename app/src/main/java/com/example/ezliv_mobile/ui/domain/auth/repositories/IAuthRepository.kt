package com.example.ezliv_mobile.ui.domain.auth.repositories

import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.auth.models.Token
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import retrofit2.Response

interface IAuthRepository {
    suspend fun login(loginRequest: LoginRequest): Response<Token>

    suspend fun firstLogin(id: String, newPassword: NewPassword): Response<Unit>
}