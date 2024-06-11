package com.example.ezliv_mobile.ui.data.reserva

import com.example.ezliv_mobile.ui.domain.reserva.models.ReservaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReservaService {

    @POST("condominiums/common-areas/reserves?commonAreaId={commonAreaId}&residentId={residentId}")
    suspend fun novaReserva(@Path("commonAreaId") commonAreaId: String, @Path("residentId") residentId: String ) : Response<Unit>

    @GET("/condominiums/common-areas/reserves/resident/{id}")
    suspend fun getMyReserves(@Path("id") id: String): Response<List<ReservaModel>>

    @DELETE("/condominiums/common-areas/reserves/{id}")
    suspend fun deleteReserva(@Path("id") id: String): Response<Unit>
}