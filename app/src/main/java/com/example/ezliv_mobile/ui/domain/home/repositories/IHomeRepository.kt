package com.example.ezliv_mobile.ui.domain.home.repositories

import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import retrofit2.Response

interface IHomeRepository {
    suspend fun getUserById(id: String): Response<UserModel>

    suspend fun getNotices(condominiumId : String) : Response<List<NoticeModel>>
}