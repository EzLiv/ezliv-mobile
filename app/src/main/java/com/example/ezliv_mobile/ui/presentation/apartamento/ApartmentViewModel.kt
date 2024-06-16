package com.example.ezliv_mobile.ui.presentation.apartamento

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.domain.apartment.repositories.IApartmentRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.results.ResidentResult
import com.example.ezliv_mobile.ui.presentation.entregas.results.PackagesResult
import kotlinx.coroutines.launch

class ApartmentViewModel(private val apartmentRepository: IApartmentRepository, context: Context) : ViewModel() {
    var residentResult = MutableLiveData<ResidentResult>(ResidentResult.Loading)
        private set

    private val preferencesManager = PreferencesManager(context)

    fun getResidents(){
        viewModelScope.launch {
            try {
                residentResult.value = ResidentResult.Loading
                val condominiumId = preferencesManager.getData("condominiumId", "")
                val apartmentId = preferencesManager.getData("apartmentId", "")
                val response = apartmentRepository.getResidents(condominiumId, apartmentId)
                if (response.isSuccessful) {
                    residentResult.value =  ResidentResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao fazer get packages")
                }
            } catch (e: Exception) {
                residentResult.value = ResidentResult.Error(e.message ?: "")
            }
        }
    }
}