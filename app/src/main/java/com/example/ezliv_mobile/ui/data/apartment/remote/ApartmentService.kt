package com.example.ezliv_mobile.ui.data.apartment.remote

import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorUpdateModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("condominium/apartments/visitors")
    suspend fun getVisitors(@Query("apartment") apartmentId: String ): Response<List<VisitorModel>>

    @GET("condominium/apartments/visitors/{id}")
    suspend fun getVisitorById(@Path("id") visitorId: String): Response<VisitorModel>

    @PUT("condominium/apartments/visitors/{id}")
    suspend fun updateVisitorById(@Path("id") visitorId: String, @Body updateVisitor : VisitorUpdateModel): Response<VisitorModel>

    @DELETE("condominium/apartments/visitors/{id}")
    suspend fun deleteVisitorById(@Path("id") visitorId: String): Response<Unit>

    @POST("condominium/apartments/visitors")
    suspend fun createVisitor(@Query("apartment") apartmentId: String, @Body newVisitorModel: NewVisitorModel): Response<Unit>
}