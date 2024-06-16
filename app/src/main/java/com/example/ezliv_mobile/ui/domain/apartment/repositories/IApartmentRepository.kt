package com.example.ezliv_mobile.ui.domain.apartment.repositories

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import retrofit2.Response

interface IApartmentRepository {
    suspend fun getResidents(condominiumId: String, apartmentId: String) : Response<List<Resident>>
}