package com.example.ezliv_mobile.ui.domain.morador.repositories

import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import retrofit2.Response

interface IMoradorRepository {
    suspend fun getAllResidentsById(apartmentId: String, condominiumId: String): Response<List<MoradorModel>>

    suspend fun getApartmentById(id: String): Response<MoradorModel>

    suspend fun newResident(apartmentId: String, condominiumId: String): Response<Unit>

    suspend fun updateResident(id: String): Response<Unit>

    suspend fun deleteResident(id: String): Response<Unit>
}