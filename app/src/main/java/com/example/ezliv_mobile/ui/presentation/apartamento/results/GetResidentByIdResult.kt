package com.example.ezliv_mobile.ui.presentation.apartamento.results

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident

interface GetResidentByIdResult {
    data class Success(val data: Resident?) : GetResidentByIdResult
    data class Error(val message: String) : GetResidentByIdResult
    object Loading : GetResidentByIdResult
}