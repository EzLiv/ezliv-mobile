package com.example.ezliv_mobile.ui.presentation.reservas.results

sealed interface ReservaResult {
    object Loading : ReservaResult
    data class Success(val data: Unit) : ReservaResult
    data class Error(val error: String) : ReservaResult
}