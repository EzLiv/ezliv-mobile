package com.example.ezliv_mobile.ui.data.home.remote

import com.example.ezliv_mobile.ui.data.auth.remote.UserService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import retrofit2.Response

class HomeRepository : IHomeRepository {
    private val api by lazy {
        Api.getInstance().create(HomeService::class.java)
    }

    override suspend fun getUserById(id: String): Response<UserModel> {
        return api.getUserById(id)
    }

    override suspend fun getNotices(condominiumId: String): Response<List<NoticeModel>> {
        return api.getNotices(condominiumId)
    }
}