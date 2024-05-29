package com.example.ezliv_mobile.ui.data.morador.remote

import com.example.ezliv_mobile.ui.data.home.remote.HomeService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import com.example.ezliv_mobile.ui.domain.morador.repositories.IMoradorRepository
import retrofit2.Response

class MoradorRepository : IMoradorRepository {
    private val api by lazy {
        Api.getInstance().create(MoradorService::class.java)
    }

    override suspend fun getAllResidentsById(
        apartmentId: String,
        condominiumId: String
    ): Response<List<MoradorModel>> {
        return api.getAllResidentsById(apartmentId, condominiumId)
    }

    override suspend fun getApartmentById(id: String): Response<MoradorModel> {
        return api.getApartmentById(id)
    }

    override suspend fun newResident(
        apartmentId: String,
        condominiumId: String
    ): Response<Unit> {
        return api.newResident(apartmentId, condominiumId)
    }

    override suspend fun updateResident(id: String): Response<Unit> {
        return api.updateResident(id)
    }

    override suspend fun deleteResident(id: String): Response<Unit> {
        return api.deleteResident(id)
    }


}