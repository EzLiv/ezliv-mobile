package com.example.ezliv_mobile.ui.data.apartment.remote

import com.example.ezliv_mobile.ui.data.entregas.remote.EntregasService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.repositories.IApartmentRepository
import retrofit2.Response

class ApartmentRepository : IApartmentRepository {
    private val api by lazy {
        Api.getInstance().create(ApartmentService::class.java)
    }

    override suspend fun getResidents(
        condominiumId: String,
        apartmentId: String
    ): Response<List<Resident>> {
        return api.getResidents(condominiumId, apartmentId)
    }
}