package com.example.ezliv_mobile.ui.data.apartment.remote

import com.example.ezliv_mobile.ui.data.entregas.remote.EntregasService
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorUpdateModel
import com.example.ezliv_mobile.ui.domain.apartment.repositories.IApartmentRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.results.UpdateResident
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

    override suspend fun getResidentById(residentId: String): Response<Resident> {
        return api.getResidentById(residentId)
    }

    override suspend fun updateResident(
        residentId: String,
        updateResident: UpdateResidentModel
    ): Response<Unit> {
        return api.updateResident(residentId, updateResident)
    }

    override suspend fun getVisitors(apartmentId: String): Response<List<VisitorModel>> {
        return api.getVisitors(apartmentId)
    }

    override suspend fun getVisitorById(vistorId: String): Response<VisitorModel> {
        return api.getVisitorById(vistorId)
    }

    override suspend fun updateVisitorById(
        vistorId: String,
        updateModel: VisitorUpdateModel
    ): Response<VisitorModel> {
        return api.updateVisitorById(vistorId, updateModel)
    }

    override suspend fun deleteVisitorById(vistorId: String): Response<Unit> {
        return api.deleteVisitorById(vistorId)
    }

    override suspend fun createVisitor(
        visitorId: String,
        newVisitorModel: NewVisitorModel
    ): Response<Unit> {
        return api.createVisitor(visitorId, newVisitorModel)
    }
}