package com.example.ezliv_mobile.ui.data.reserva

import com.example.ezliv_mobile.ui.data.auth.remote.UserService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.reserva.models.ReservaModel
import com.example.ezliv_mobile.ui.domain.reserva.repositories.IReservaRepository
import retrofit2.Response


class ReservaRepository : IReservaRepository {
    private val api by lazy {
        Api.getInstance().create(ReservaService::class.java)
    }

    override suspend fun novaReserva(commonAreaId: String, residentId: String): Response<Unit> {
        return api.novaReserva(commonAreaId, residentId)
    }

    override suspend fun getMyReserves(id: String): Response<List<ReservaModel>>{
        return api.getMyReserves(id)
    }

    override suspend fun deleteReserva(id: String): Response<Unit> {
        return api.deleteReserva(id)
    }
}
