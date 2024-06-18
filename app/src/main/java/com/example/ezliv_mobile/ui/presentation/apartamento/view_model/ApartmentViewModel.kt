package com.example.ezliv_mobile.ui.presentation.apartamento.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorUpdateModel
import com.example.ezliv_mobile.ui.domain.apartment.repositories.IApartmentRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.results.CreateVisitorResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.DeleteUserResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetResidentByIdResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetVisitorByIdResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetVisitorsResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.ResidentResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.UpdateResident
import com.example.ezliv_mobile.ui.presentation.apartamento.results.UpdateVisitorResult
import com.example.ezliv_mobile.ui.presentation.entregas.results.PackagesResult
import kotlinx.coroutines.launch

class ApartmentViewModel(private val apartmentRepository: IApartmentRepository, context: Context) :
    ViewModel() {
    var residentResult = MutableLiveData<ResidentResult>(ResidentResult.Loading)
        private set

    var residentByIdResult = MutableLiveData<GetResidentByIdResult>(GetResidentByIdResult.Loading)
        private set

    var updateResidentByIdResult = MutableLiveData<UpdateResident>(UpdateResident.Loading)
        private set

    var getVisitorsResult = MutableLiveData<GetVisitorsResult>(GetVisitorsResult.Loading)
        private set

    var getVisitorsByIdResult = MutableLiveData<GetVisitorByIdResult>(GetVisitorByIdResult.Loading)
        private set

    var updateVisitorResult = MutableLiveData<UpdateVisitorResult>(UpdateVisitorResult.Loading)
        private set

    var deleteUserResult = MutableLiveData<DeleteUserResult>(DeleteUserResult.Loading)
        private set


    var createVisitorResult = MutableLiveData<CreateVisitorResult>(null)
        private set

    private val preferencesManager = PreferencesManager(context)

    fun getResidents() {
        viewModelScope.launch {
            try {
                residentResult.value = ResidentResult.Loading
                val condominiumId = preferencesManager.getData("condominiumId", "")
                val apartmentId = preferencesManager.getData("apartmentId", "")
                val response = apartmentRepository.getResidents(condominiumId, apartmentId)
                if (response.isSuccessful) {
                    residentResult.value = ResidentResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao fazer get packages")
                }
            } catch (e: Exception) {
                residentResult.value = ResidentResult.Error(e.message ?: "")
            }
        }
    }

    fun getResidentById(id: String) {
        viewModelScope.launch {
            try {
                residentByIdResult.value = GetResidentByIdResult.Loading
                val condominiumId = preferencesManager.getData("condominiumId", "")
                val apartmentId = preferencesManager.getData("apartmentId", "")
                val response = apartmentRepository.getResidentById(id)
                if (response.isSuccessful) {
                    residentByIdResult.value = GetResidentByIdResult.Success(response.body())
                } else {
                    throw Exception("Erro ao fazer getResidentById")
                }
            } catch (e: Exception) {
                residentResult.value = ResidentResult.Error(e.message ?: "")
            }
        }
    }

    fun updateResident(id: String, updateResident: UpdateResidentModel) {
        viewModelScope.launch {
            try {
                updateResidentByIdResult.value = UpdateResident.Loading
                val response = apartmentRepository.updateResident(id, updateResident)
                if (response.isSuccessful) {
                    updateResidentByIdResult.value = UpdateResident.Success(Unit)
                } else {
                    throw Exception("Erro ao atualizar residente")
                }
            } catch (e: Exception) {
                updateResidentByIdResult.value = UpdateResident.Error(e.message ?: "")
            }
        }
    }

    fun getAllVisitors() {
        viewModelScope.launch {
            try {
                val apartmentId = preferencesManager.getData("apartmentId", "")
                getVisitorsResult.value = GetVisitorsResult.Loading
                val response = apartmentRepository.getVisitors(apartmentId)
                if (response.isSuccessful) {
                    getVisitorsResult.value =
                        GetVisitorsResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao buscar visitantes")
                }
            } catch (e: Exception) {
                getVisitorsResult.value = GetVisitorsResult.Error(e.message ?: "")
            }
        }
    }

    fun getVisitorById(visitorId: String) {
        viewModelScope.launch {
            try {
                getVisitorsByIdResult.value = GetVisitorByIdResult.Loading
                val response = apartmentRepository.getVisitorById(visitorId)
                if (response.isSuccessful) {
                    getVisitorsByIdResult.value = GetVisitorByIdResult.Success(response.body())
                } else {
                    throw Exception("Erro ao buscar visitante")
                }
            } catch (e: Exception) {
                getVisitorsByIdResult.value = GetVisitorByIdResult.Error(e.message ?: "")
            }
        }
    }

    fun updateVisitorById(visitorId: String, visitorUpdateModel: VisitorUpdateModel) {
        viewModelScope.launch {
            try {
                updateVisitorResult.value = UpdateVisitorResult.Loading
                val response = apartmentRepository.updateVisitorById(visitorId, visitorUpdateModel)
                if (response.isSuccessful) {
                    updateVisitorResult.value = UpdateVisitorResult.Success(Unit)
                } else {
                    throw Exception("Erro ao buscar atualizar visitante")
                }
            } catch (e: Exception) {
                updateVisitorResult.value = UpdateVisitorResult.Error(e.message ?: "")
            }
        }
    }

    fun deleteVisitorById(id: String) {
        viewModelScope.launch {
            try {
                deleteUserResult.value = DeleteUserResult.Loading
                val response = apartmentRepository.deleteVisitorById(id)
                if (response.isSuccessful) {
                    deleteUserResult.value = DeleteUserResult.Success(Unit)
                } else {
                    throw Exception("Erro ao buscar atualizar visitante")
                }
            } catch (e: Exception) {
                deleteUserResult.value = DeleteUserResult.Error(e.message ?: "")
            }
        }
    }

    fun createVisitor(newVisitorModel: NewVisitorModel){
        viewModelScope.launch {
            try {
                val apartmentId = preferencesManager.getData("apartmentId", "")
                val response = apartmentRepository.createVisitor(apartmentId, newVisitorModel)
                if (response.isSuccessful) {
                    createVisitorResult.value = CreateVisitorResult.Success(Unit)
                } else {
                    throw Exception("Erro ao criar visitante")
                }
            } catch (e: Exception) {
                createVisitorResult.value = CreateVisitorResult.Error(e.message ?: "")
            }
        }
    }
}