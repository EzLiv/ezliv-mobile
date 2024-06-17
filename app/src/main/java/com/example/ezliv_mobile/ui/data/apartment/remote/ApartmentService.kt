package com.example.ezliv_mobile.ui.data.apartment.remote

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApartmentService {
    @GET("condominiums/towers/apartments/residents")
    suspend fun getResidents(@Query("condominium") condominiumId: String,
                                      @Query("apartment") apartmentId: String ): Response<List<Resident>>

    @GET("condominiums/towers/apartments/residents/{id}")
    suspend fun getResidentById(@Path("id") residentId: String): Response<Resident>

    @PUT("condominiums/towers/apartments/residents/{id}")
    suspend fun updateResident(@Path("id") residentId: String, @Body updateResident: UpdateResidentModel): Response<Unit>
}