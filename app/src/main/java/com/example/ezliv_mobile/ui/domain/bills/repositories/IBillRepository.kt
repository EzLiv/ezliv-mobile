package com.example.ezliv_mobile.ui.domain.bills.repositories

import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.bills.models.BillModel
import retrofit2.Response

interface IBillRepository {
    suspend fun getBills(loginRequest: LoginRequest): Response<List<BillModel>>
}