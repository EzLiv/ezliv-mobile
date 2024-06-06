package com.example.ezliv_mobile.ui.data.visitante.remote

import com.example.ezliv_mobile.ui.data.morador.remote.MoradorService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import com.example.ezliv_mobile.ui.domain.visitante.repositories.IVisitanteRepository
import retrofit2.Response

class VisitanteRepository : IVisitanteRepository {
    private val api by lazy {
        Api.getInstance().create(VisitanteService::class.java)
    }

    override suspend fun getAllVisitorsByApartmentId(
        apartmentId: String
    ): Response<List<MoradorModel>> {
        return api.getAllVisitorsByApartmentId(apartmentId)
    }

    override suspend fun newRequest(id: String): Response<MoradorModel> {
        return api.newRequest(id)
    }

    override suspend fun newVisitor(id: String): Response<Unit> {
        return api.newVisitor(id)
    }

    override suspend fun updateVisitor(id: String): Response<Unit> {
        return api.updateVisitor(id)
    }

    override suspend fun deleteVisitor(id: String): Response<Unit>{
        return api.deleteVisitor(id)
    }
}