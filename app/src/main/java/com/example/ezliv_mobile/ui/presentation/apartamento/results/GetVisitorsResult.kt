package com.example.ezliv_mobile.ui.presentation.apartamento.results

import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel

interface GetVisitorsResult {
    data class Success(val data: List<VisitorModel>) : GetVisitorsResult
    data class Error(val message: String) : GetVisitorsResult
    object Loading : GetVisitorsResult
}