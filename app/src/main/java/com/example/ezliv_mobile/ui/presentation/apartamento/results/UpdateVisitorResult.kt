package com.example.ezliv_mobile.ui.presentation.apartamento.results

interface UpdateVisitorResult {
    data class Success(val data: Unit) : UpdateVisitorResult
    data class Error(val message: String) : UpdateVisitorResult
    object Loading : UpdateVisitorResult
}