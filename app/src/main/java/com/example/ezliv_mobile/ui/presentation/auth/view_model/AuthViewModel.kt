package com.example.ezliv_mobile.ui.presentation.auth.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezliv_mobile.ui.domain.auth.models.LoginRequest
import com.example.ezliv_mobile.ui.domain.auth.models.NewPassword
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import kotlinx.coroutines.launch
import android.content.Context
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.configurations.PreferencesManager
import com.example.ezliv_mobile.ui.presentation.auth.result.LoginResult

class AuthViewModel(private val authRepository: IAuthRepository, context: Context) : ViewModel() {

    var result = MutableLiveData<LoginResult>(LoginResult.Loading)
        private set

    private val preferencesManager = PreferencesManager(context)
    fun login(email: String, password: String, navController : NavController) {
        viewModelScope.launch {
            try {
                result.value = LoginResult.Loading
                val loginRequest = LoginRequest(email, password)
                val response = authRepository.login(loginRequest)
                if (response.isSuccessful) {
                    result.value = LoginResult.Success(response.body()!!)
                    preferencesManager.saveData("id", response.body()?.id ?: "")

                    val isSucess = result.value as LoginResult.Success
                    if (isSucess.token.firstLoginAlreadyDone) {
                        navController.navigate("mural") {
                            launchSingleTop = true
                            popUpTo("login")
                        }
                    } else {
                        navController.navigate("password-change") {
                            launchSingleTop = true
                            popUpTo("login")
                        }
                    }

                } else {
                    throw Exception("Erro ao fazer login")
                }
            } catch (e: Exception) {
                result.value = LoginResult.Error(e.message ?: "")
            }
        }

    }

    fun newPassword(newPassword: String, onNavigate: () -> Unit) {
        viewModelScope.launch {
            try {
                result.value = LoginResult.Loading
                val passwordBody = NewPassword(newPassword)
                val id: String = preferencesManager.getData("id", "")
                val response = authRepository.firstLogin(id, passwordBody)
                if (response.isSuccessful) {
                    result.value = LoginResult.SuccessChangePassword(true)

                    onNavigate()
                } else {
                    throw Exception("Erro ao trocar senha!")
                }
            } catch (e: Exception) {
                result.value = LoginResult.ErrorChangePassword(e.message ?: "")
            }
        }
    }
}