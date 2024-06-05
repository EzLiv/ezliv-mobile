package com.example.ezliv_mobile.ui.presentation.entregas.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.domain.entregas.repositories.IEntregasRepository
import com.example.ezliv_mobile.ui.presentation.entregas.results.PackagesResult
import com.example.ezliv_mobile.ui.presentation.home.results.GetUserResult
import kotlinx.coroutines.launch

class EntregasViewModel (private val entregasRepository: IEntregasRepository, context: Context)  : ViewModel(){

    private val preferencesManager = PreferencesManager(context)

    var result = MutableLiveData<GetUserResult>(GetUserResult.Loading)
        private set

    var entregasResult = MutableLiveData<PackagesResult>(PackagesResult.Loading)
        private set

    fun getPackagesByApartment(){
        viewModelScope.launch {
            try {
                entregasResult.value = PackagesResult.Loading
                val condominiumId = preferencesManager.getData("condominiumId", "")
                val apartmentId = preferencesManager.getData("apartmentId", "")
                val response = entregasRepository.getByApartmentId(condominiumId, apartmentId)
                if (response.isSuccessful) {
                    entregasResult.value =  PackagesResult.Success(response.body() ?: emptyList())
                } else {
                    throw Exception("Erro ao fazer get packages")
                }
            } catch (e: Exception) {
                entregasResult.value = PackagesResult.Error(e.message ?: "")
            }
        }
    }
}