package com.example.ezliv_mobile.ui.domain.reserva.repositories

import com.example.ezliv_mobile.ui.domain.reserva.models.ReservaModel
import retrofit2.Response


interface IReservaRepository {
    suspend fun novaReserva(commonAreaId: String, residentId: String): Response<Unit>

    suspend fun getMyReserves(id: String): Response<List<ReservaModel>>

    suspend fun deleteReserva(id: String): Response<Unit>

}