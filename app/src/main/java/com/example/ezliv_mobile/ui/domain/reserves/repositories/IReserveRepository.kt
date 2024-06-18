package com.example.ezliv_mobile.ui.domain.reserves.repositories


import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel
import retrofit2.Response


interface IReserveRepository {
    suspend fun getCommonAreas(condominiumId : String) : Response<List<CommonAreaModel>>

    suspend fun createReserve( commonAreaId: String, residentId: String, newReserveModel: NewReserveModel) : Response<Unit>

    suspend fun getAllReserves(residentId: String) : Response<List<ReserveModel>>

    suspend fun deleteReserve(reserveId: String) : Response<Unit>
}