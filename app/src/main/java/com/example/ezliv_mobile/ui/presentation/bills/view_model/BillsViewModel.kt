package com.example.ezliv_mobile.ui.presentation.bills.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.data.bills.remote.BillRepository
import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.bills.repositories.IBillRepository
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import com.example.ezliv_mobile.ui.presentation.bills.result.BillsResult
import com.example.ezliv_mobile.ui.presentation.home.results.GetUserResult
import kotlinx.coroutines.launch

class BillsViewModel(private val billsRepository: IBillRepository, context: Context) : ViewModel() {

    val preferencesManager = PreferencesManager(context)

    var result = MutableLiveData<BillsResult>(null)
        private set

    fun getBills(email : String, password : String) {
        viewModelScope.launch {
            try {
                result.value = BillsResult.Loading
                val response = billsRepository.getBills(LoginRequest(email, password))
                if (response.isSuccessful) {
                    result.value = BillsResult.Success(response.body() ?: emptyList())
                    preferencesManager.saveData("electricity_mail", email)
                    preferencesManager.saveData("electricity_password", password)
                } else {
                    throw Exception("Erro ao buscar contas")
                }
            } catch (e: Exception) {
                result.value = BillsResult.Error(e.message ?: "")
            }
        }
    }
}