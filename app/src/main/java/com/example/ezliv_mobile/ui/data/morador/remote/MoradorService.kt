package com.example.ezliv_mobile.ui.data.morador.remote

import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MoradorService {
    @GET("/condominiums/towers/apartments/residents?apartment={apartment}&condominium={condominium}")
    suspend fun getAllResidentsById(@Path("apartment") apartmentId: String, @Path("condominium") condominiumId: String): Response<List<MoradorModel>>
    @GET("/condominiums/towers/apartments/residents/{id}")
    suspend fun getApartmentById(@Path("id") id: String): Response<MoradorModel>

    @POST("/condominiums/towers/apartments/residents?apartment={apartment}&condominium={condominium}")
    suspend fun newResident(@Path("apartment") apartmentId: String, @Path("condominium") condominiumId: String): Response<Unit>

    @PUT("/condominiums/towers/apartments/residents/{id}")
    suspend fun updateResident(@Path("id") id: String): Response<Unit>

    @DELETE("/condominiums/towers/apartments/residents/{id}")
    suspend fun deleteResident(@Path("id") id: String): Response<Unit>
}