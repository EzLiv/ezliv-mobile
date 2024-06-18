package com.example.ezliv_mobile.ui.domain.reserves.repositories


import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import retrofit2.Response


interface IReserveRepository {
    suspend fun getCommonAreas(condominiumId : String) : Response<List<CommonAreaModel>>

    suspend fun createReserve( commonAreaId: String, residentId: String, newReserveModel: NewReserveModel) : Response<Unit>
}