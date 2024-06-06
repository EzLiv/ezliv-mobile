package com.example.ezliv_mobile.ui.data.entregas.remote

import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntregasService {

    @GET("condominium/{condominiumId}/packages/apartment/{apartmentId}")
    suspend fun getPackageByApartment(@Path("condominiumId") condominiumId: String,
                                      @Path("apartmentId") apartmentId: String ): Response<List<PackageModel>>
}