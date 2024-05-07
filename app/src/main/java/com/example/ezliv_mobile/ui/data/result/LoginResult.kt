package com.example.ezliv_mobile.ui.data.result

import com.example.ezliv_mobile.ui.data.models.Token

sealed interface LoginResult {
    data class Success(val token: Token) : LoginResult
    data class SuccessChangePassword(val success: Boolean) : LoginResult
    data class Error(val message: String) : LoginResult
    data class ErrorChangePassword(val message: String) : LoginResult
    object Loading : LoginResult
}