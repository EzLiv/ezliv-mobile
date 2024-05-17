package com.example.ezliv_mobile.ui.data.auth.remote

import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.auth.models.Token
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import retrofit2.Response

class AuthRepository : IAuthRepository {
    private val api by lazy {
        Api.getInstance().create(UserService::class.java)
    }

    override suspend fun login(loginRequest: LoginRequest): Response<Token> {
        val response = api.login(loginRequest)
        if (response.isSuccessful) {
            Api.setAuthToken(response.body()?.token ?: "")
        }
        return response
    }

    override suspend fun firstLogin(id: String, newPassword: NewPassword): Response<Unit> {
        return api.firstLogin(id, newPassword)
    }

}