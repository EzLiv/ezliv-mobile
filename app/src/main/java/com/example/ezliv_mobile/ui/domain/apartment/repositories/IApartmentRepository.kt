package com.example.ezliv_mobile.ui.domain.apartment.repositories

import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorUpdateModel
import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import com.example.ezliv_mobile.ui.presentation.apartamento.results.UpdateResident
import retrofit2.Response

interface IApartmentRepository {
    suspend fun getResidents(condominiumId: String, apartmentId: String) : Response<List<Resident>>

    suspend fun getResidentById(residentId: String) : Response<Resident>

    suspend fun updateResident(residentId: String, updateResident: UpdateResidentModel) : Response<Unit>
    suspend fun getVisitors(apartmentId: String) : Response<List<VisitorModel>>
    suspend fun getVisitorById(vistorId: String) : Response<VisitorModel>

    suspend fun updateVisitorById(vistorId: String, updateModel: VisitorUpdateModel) : Response<VisitorModel>

    suspend fun deleteVisitorById(vistorId: String) : Response<Unit>

    suspend fun createVisitor(visitorId: String, newVisitorModel: NewVisitorModel) : Response<Unit>
}