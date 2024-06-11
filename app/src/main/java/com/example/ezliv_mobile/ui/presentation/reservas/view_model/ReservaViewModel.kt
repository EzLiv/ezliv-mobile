package com.example.ezliv_mobile.ui.presentation.reservas.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.domain.reserva.repositories.IReservaRepository
import com.example.ezliv_mobile.ui.presentation.home.results.NoticesResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetReservaResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.ReservaResult
import kotlinx.coroutines.launch
import java.util.Date

class ReservaViewModel(private val ReservaRepository: IReservaRepository, context: Context) : ViewModel() {

    var postEventResult = MutableLiveData<ReservaResult>(ReservaResult.Loading)
        private set
    var reservasResult = MutableLiveData<GetReservaResult>(GetReservaResult.Loading)
        private set

    private val preferencesManager = PreferencesManager(context)
    fun novaReserva(commonAreaId: String, residentId: String) {
        viewModelScope.launch {
            try {
                postEventResult.value = ReservaResult.Loading
                val response = ReservaRepository.novaReserva(commonAreaId, residentId)
                if (response.isSuccessful) {
                    postEventResult.value = ReservaResult.Success(response.body()!!)
                } else {
                    throw Exception("Erro ao postar evento")
                }
            } catch (e: Exception) {
                postEventResult.value = ReservaResult.Error(e.message ?: "")
            }
        }
    }

    fun getMyReserves(){
        viewModelScope.launch {
            try {
                reservasResult.value = GetReservaResult.Loading
                val commonAreaId = preferencesManager.getData("commonAreaId", "")
                val response = ReservaRepository.getMyReserves(commonAreaId)
                if (response.isSuccessful) {
                    reservasResult.value =  GetReservaResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao fazer get reservas")
                }
            } catch (e: Exception) {
                reservasResult.value = GetReservaResult.Error(e.message ?: "")
            }
        }
    }

    fun deleteReserva(id: String){
            viewModelScope.launch {
                try {
                    postEventResult.value = ReservaResult.Loading
                    val response = ReservaRepository.deleteReserva(id)
                    if (response.isSuccessful) {
                        val commonAreaId = preferencesManager.getData("commonAreaId", "")
                        val updatedResponse = ReservaRepository.deleteReserva(id)
                        if (updatedResponse.isSuccessful) {
                            postEventResult.value = ReservaResult.Success(response.body()!!)
                        } else {
                            throw Exception("Erro ao atualizar a lista de reservas após a exclusão")
                        }
                    } else {
                        throw Exception("Erro ao deletar reserva")
                    }
                } catch (e: Exception) {
                    postEventResult.value = ReservaResult.Error(e.message ?: "")
                }
            }
        }

    }
