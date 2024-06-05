package com.example.ezliv_mobile.ui.presentation.home.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.presentation.home.results.GetUserResult
import com.example.ezliv_mobile.ui.presentation.home.results.NoticesResult
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: IHomeRepository, context: Context) : ViewModel() {

        private val preferencesManager = PreferencesManager(context)

        var result = MutableLiveData<GetUserResult>(GetUserResult.Loading)
            private set

        var noticesResult = MutableLiveData<NoticesResult>(NoticesResult.Loading)
            private set


        fun getUserById() {
            viewModelScope.launch {
                try {
                    result.value = GetUserResult.Loading
                    val id = preferencesManager.getData("id", "")
                    val response = homeRepository.getUserById(id)
                    if (response.isSuccessful) {
                        result.value = GetUserResult.Success(response.body()!!)
                        preferencesManager.saveData("id", response.body()?.id ?: "")
                        preferencesManager.saveData("name", response.body()?.fullName ?: "")
                        preferencesManager.saveData("email", response.body()?.email ?: "")
                        preferencesManager.saveData("condominiumId", response.body()?.condominiumId ?: "")
                        preferencesManager.saveData("apartmentId", response.body()?.apartmentId ?: "")

                    } else {
                        throw Exception("Erro ao fazer login")
                    }
                } catch (e: Exception) {
                    result.value = GetUserResult.Error(e.message ?: "")
                }
            }
        }

        fun getNotices(){
            viewModelScope.launch {
                try {
                    noticesResult.value = NoticesResult.Loading
                    val condominiumId = preferencesManager.getData("condominiumId", "")
                    val response = homeRepository.getNotices(condominiumId)
                    if (response.isSuccessful) {
                        noticesResult.value =  NoticesResult.Success(response.body() ?: emptyList())
                    } else {
                        throw Exception("Erro ao fazer get notices")
                    }
                } catch (e: Exception) {
                    noticesResult.value = NoticesResult.Error(e.message ?: "")
                }
            }
        }

        fun logout(){
            preferencesManager.clearData()
        }
}