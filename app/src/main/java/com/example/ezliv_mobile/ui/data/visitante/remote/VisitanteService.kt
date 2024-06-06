package com.example.ezliv_mobile.ui.data.visitante.remote

import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VisitanteService {
    @GET("/condominium/apartments/visitors?apartment={apartment}")
    suspend fun getAllVisitorsByApartmentId(@Path("apartment") apartmentId: String): Response<List<MoradorModel>>
    @GET("/condominium/apartments/visitors/{id}")
    suspend fun newRequest(@Path("id") id: String): Response<MoradorModel>

    @POST("/condominium/apartments/visitors?apartment={apartment}")
    suspend fun newVisitor(@Path("apartment") apartmentId: String): Response<Unit>

    @PUT("/condominium/apartments/visitors/{id}")
    suspend fun updateVisitor(@Path("id") id: String): Response<Unit>

    @DELETE("/condominium/apartments/visitors/{id}")
    suspend fun deleteVisitor(@Path("id") id: String): Response<Unit>
}