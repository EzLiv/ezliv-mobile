package com.example.ezliv_mobile.ui.data.repo_impls

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.NewPassword
import com.example.ezliv_mobile.ui.data.models.NoticeModel
import com.example.ezliv_mobile.ui.data.models.Token
import com.example.ezliv_mobile.ui.data.models.UserModel
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.data.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.data.services.UserService
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

    override suspend fun getUserById(id: String): Response<UserModel> {
        return api.getUserById(id)
    }

    override suspend fun getNotices(condominiumId: String): Response<List<NoticeModel>> {
        return api.getNotices(condominiumId)
    }
}