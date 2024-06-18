package com.example.ezliv_mobile.ui.data.bills.remote

import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.bills.models.BillModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface BillService {
    @POST("bills/electricity")
    suspend fun getBills(@Body loginRequest: LoginRequest): Response<List<BillModel>>
}