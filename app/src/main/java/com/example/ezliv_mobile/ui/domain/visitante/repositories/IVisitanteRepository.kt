package com.example.ezliv_mobile.ui.domain.visitante.repositories

import com.example.ezliv_mobile.ui.domain.morador.models.MoradorModel
import retrofit2.Response

interface IVisitanteRepository {
    suspend fun getAllVisitorsByApartmentId(apartmentId: String): Response<List<MoradorModel>>

    suspend fun newRequest(id: String): Response<MoradorModel>

    suspend fun newVisitor(id: String): Response<Unit>

    suspend fun updateVisitor(id: String): Response<Unit>

    suspend fun deleteVisitor(id: String): Response<Unit>
}