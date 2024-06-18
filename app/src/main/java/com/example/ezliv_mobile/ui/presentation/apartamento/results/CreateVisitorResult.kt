package com.example.ezliv_mobile.ui.presentation.apartamento.results

interface CreateVisitorResult {
    data class Success(val data: Unit) : CreateVisitorResult
    data class Error(val message: String) : CreateVisitorResult
    object Loading : CreateVisitorResult
}