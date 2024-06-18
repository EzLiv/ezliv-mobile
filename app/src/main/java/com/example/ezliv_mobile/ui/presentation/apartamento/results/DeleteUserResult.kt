package com.example.ezliv_mobile.ui.presentation.apartamento.results

interface DeleteUserResult {
    data class Success(val data: Unit) : DeleteUserResult
    data class Error(val message: String) : DeleteUserResult
    object Loading : DeleteUserResult
}