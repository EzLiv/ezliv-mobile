package com.example.ezliv_mobile.ui.domain.apartment.repositories

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import com.example.ezliv_mobile.ui.presentation.apartamento.results.UpdateResident
import retrofit2.Response

interface IApartmentRepository {
    suspend fun getResidents(condominiumId: String, apartmentId: String) : Response<List<Resident>>

    suspend fun getResidentById(residentId: String) : Response<Resident>

    suspend fun updateResident(residentId: String, updateResident: UpdateResidentModel) : Response<Unit>

}