package com.example.ezliv_mobile.ui.presentation.reservas.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.domain.entregas.repositories.IEntregasRepository
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import com.example.ezliv_mobile.ui.domain.reserves.repositories.IReserveRepository
import com.example.ezliv_mobile.ui.presentation.entregas.results.PackagesResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.CreateReserveResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.DeleteReserveResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetCommonAreasResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetReservesResult
import kotlinx.coroutines.launch

class ReserveViewModel (private val reserveRepository: IReserveRepository, context: Context)  : ViewModel() {
    private val preferencesManager = PreferencesManager(context)

    var getCommonAreasResult = MutableLiveData<GetCommonAreasResult>(null)
        private set

    var createReserveResult = MutableLiveData<CreateReserveResult>(null)
        private set

    var getAllReservesResult = MutableLiveData<GetReservesResult>(null)
        private set

    var deleteReserveResult = MutableLiveData<DeleteReserveResult>(null)
        private set

    fun getCommonAreas(){
        viewModelScope.launch {
            try {
                getCommonAreasResult.value = GetCommonAreasResult.Loading
                val condominiumId = preferencesManager.getData("condominiumId", "")
                val response = reserveRepository.getCommonAreas(condominiumId)
                if (response.isSuccessful) {
                    getCommonAreasResult.value =  GetCommonAreasResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao buscar áreas comuns")
                }
            } catch (e: Exception) {
                getCommonAreasResult.value = GetCommonAreasResult.Error(e.message ?: "")
            }
        }
    }

    fun createReserve(commonAreaId : String, newReserveModel : NewReserveModel){
        viewModelScope.launch {
            try {
                createReserveResult.value = CreateReserveResult.Loading
                val residentId = preferencesManager.getData("id", "")
                val response = reserveRepository.createReserve(commonAreaId, residentId, newReserveModel)
                if (response.isSuccessful) {
                    createReserveResult.value =  CreateReserveResult.Success(Unit)
                } else {
                    throw Exception("Erro ao buscar áreas comuns")
                }
            } catch (e: Exception) {
                createReserveResult.value = CreateReserveResult.Error(e.message ?: "")
            }
        }
    }

    fun getReserves(){
        viewModelScope.launch {
            try {
                getAllReservesResult.value = GetReservesResult.Loading
                val residentId = preferencesManager.getData("id", "")
                val response = reserveRepository.getAllReserves(residentId)
                if (response.isSuccessful) {
                    getAllReservesResult.value =  GetReservesResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao buscar reservas")
                }
            } catch (e: Exception) {
                getAllReservesResult.value = GetReservesResult.Error(e.message ?: "")
            }
        }
    }

    fun deleteReserve(reserveId : String){
        viewModelScope.launch {
            try {
                deleteReserveResult.value = DeleteReserveResult.Loading
                val response = reserveRepository.deleteReserve(reserveId)
                if (response.isSuccessful) {
                    deleteReserveResult.value =  DeleteReserveResult.Success(Unit)
                } else {
                    throw Exception("Erro ao buscar reservas")
                }
            } catch (e: Exception) {
                deleteReserveResult.value = DeleteReserveResult.Error(e.message ?: "")
            }
        }
    }
}