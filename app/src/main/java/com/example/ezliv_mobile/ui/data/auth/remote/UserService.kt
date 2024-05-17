package com.example.ezliv_mobile.ui.data.auth.remote

import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.auth.models.Token
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
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

}