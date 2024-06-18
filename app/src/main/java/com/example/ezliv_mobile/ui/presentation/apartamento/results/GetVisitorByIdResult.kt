package com.example.ezliv_mobile.ui.presentation.apartamento.results

import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel

interface GetVisitorByIdResult {
    data class Success(val data: VisitorModel?) : GetVisitorByIdResult
    data class Error(val message: String) : GetVisitorByIdResult
    object Loading : GetVisitorByIdResult
}