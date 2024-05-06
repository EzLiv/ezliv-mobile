package com.example.ezliv_mobile.ui.data.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.data.models.LoginRequest
import com.example.ezliv_mobile.ui.data.models.Token
import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.data.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.data.services.UserService
import kotlinx.coroutines.launch

class UserViewModel(private val authRepository: IAuthRepository) : ViewModel() {
    var isLoading = MutableLiveData(false)
        private set

    var errorMessage = MutableLiveData("")
        private set

    var isSucess = MutableLiveData<Token>()
        private set

    var isError = MutableLiveData(false)
        private set

    fun login(email: String, password: String, onNavigate: () -> Unit){
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = authRepository.login(loginRequest)
                if (response.isSuccessful) {
                    isSucess.value = response.body()
                    isLoading.value = false
                    onNavigate()
                } else {
                    throw Exception("Erro ao fazer login")
                }
            } catch (e: Exception) {
                isError.value = true
                errorMessage.value = e.message
                isLoading.value = false
            }
        }

    }
}