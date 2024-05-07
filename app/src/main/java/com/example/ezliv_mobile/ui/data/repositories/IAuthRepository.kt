package com.example.ezliv_mobile.ui.data.repositories

import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.NewPassword
import com.example.ezliv_mobile.ui.data.models.NoticeModel
import com.example.ezliv_mobile.ui.data.models.Token
import com.example.ezliv_mobile.ui.data.models.UserModel
import retrofit2.Response

interface IAuthRepository {
    suspend fun login(loginRequest: LoginRequest): Response<Token>

    suspend fun firstLogin(id: String, newPassword: NewPassword): Response<Unit>

    suspend fun getUserById(id: String): Response<UserModel>

    suspend fun getNotices(condominiumId : String) : Response<List<NoticeModel>>
}