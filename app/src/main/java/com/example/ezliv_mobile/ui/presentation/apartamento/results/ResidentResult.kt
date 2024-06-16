package com.example.ezliv_mobile.ui.presentation.apartamento.results

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident

interface ResidentResult {
    data class Success(val data: List<Resident>) : ResidentResult
    data class Error(val message: String) : ResidentResult
    object Loading : ResidentResult
}