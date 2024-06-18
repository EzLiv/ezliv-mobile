package com.example.ezliv_mobile.ui.data.reserves.remote

import com.example.ezliv_mobile.ui.data.home.remote.HomeService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel
import com.example.ezliv_mobile.ui.domain.reserves.repositories.IReserveRepository
import retrofit2.Response

class ReserveRepository : IReserveRepository {
    private val api by lazy {
        Api.getInstance().create(ReserveService::class.java)
    }

    override suspend fun getCommonAreas(condominiumId: String): Response<List<CommonAreaModel>> {
        return api.getCommonAreas(condominiumId)
    }

    override suspend fun createReserve(
        commonAreaId: String,
        residentId: String,
        newReserveModel: NewReserveModel
    ): Response<Unit> {
        return api.createReserve(commonAreaId, residentId, newReserveModel)
    }

    override suspend fun getAllReserves(residentId: String): Response<List<ReserveModel>> {
        return api.getReservesByResident(residentId)
    }

    override suspend fun deleteReserve(reserveId: String): Response<Unit> {
        return api.deleteReserveById(reserveId)
    }
}