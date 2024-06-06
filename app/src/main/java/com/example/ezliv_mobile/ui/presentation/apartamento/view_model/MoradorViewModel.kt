package com.example.ezliv_mobile.ui.presentation.apartamento.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.data.morador.remote.MoradorRepository
import com.example.ezliv_mobile.ui.domain.morador.repositories.IMoradorRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.results.MoradorResult
import kotlinx.coroutines.launch

class MoradorViewModel(private val MoradorRepository: IMoradorRepository, context: Context) : ViewModel() {

    var moradorResult = MutableLiveData<MoradorResult>(MoradorResult.Loading)

    private val preferencesManager = PreferencesManager(context)

    fun newMorador(apartmentId: String, condominiumId: String){
        viewModelScope.launch{
            try {
                moradorResult.value = MoradorResult.Loading
                val response = MoradorRepository.newResident(apartmentId,condominiumId)
                if (response.isSuccessful){
                    moradorResult.value = MoradorResult.Success(response.body()!!)
                } else {
                    throw Exception("Erro ao cadastrar novo morador")
                }
            }catch (e: Exception){

            }
        }
    }
}