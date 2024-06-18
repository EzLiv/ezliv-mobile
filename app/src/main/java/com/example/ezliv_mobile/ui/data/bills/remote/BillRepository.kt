package com.example.ezliv_mobile.ui.data.bills.remote

import com.example.ezliv_mobile.ui.data.apartment.remote.ApartmentService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.bills.models.BillModel
import com.example.ezliv_mobile.ui.domain.bills.repositories.IBillRepository
import retrofit2.Response

class BillRepository : IBillRepository {
    private val api by lazy {
        Api.getInstance().create(BillService::class.java)
    }
    override suspend fun getBills(loginRequest: LoginRequest): Response<List<BillModel>> {
       return api.getBills(loginRequest)
    }
}