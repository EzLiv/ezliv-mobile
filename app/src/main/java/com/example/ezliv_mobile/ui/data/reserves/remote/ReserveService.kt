package com.example.ezliv_mobile.ui.data.reserves.remote

import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReserveService {

    @GET("condominiums/{id}/common-areas")
    suspend fun getCommonAreas(@Path("id") condominiumId: String): Response<List<CommonAreaModel>>

    @POST("condominiums/common-areas/reserves")
    suspend fun createReserve(
        @Query("commonAreaId") commonAreaId: String,
        @Query("residentId") residentId: String,
        @Body newReserveModel: NewReserveModel
    ): Response<Unit>

    @GET("condominiums/common-areas/reserves/resident/{id}")
    suspend fun getReservesByResident(@Path("id") residentId: String): Response<List<ReserveModel>>

    @DELETE("condominiums/common-areas/reserves/{id}")
    suspend fun deleteReserveById(@Path("id") reserveId: String): Response<Unit>
}