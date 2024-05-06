package com.example.ezliv_mobile.ui.data.repo_impls

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.Token
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.data.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.data.services.UserService
import retrofit2.Response

class AuthRepository : IAuthRepository {
    private val api by lazy {
        Api.getInstance().create(UserService::class.java) }

    override suspend fun login(loginRequest: LoginRequest): Response<Token> {
        return api.login(loginRequest)
    }
}