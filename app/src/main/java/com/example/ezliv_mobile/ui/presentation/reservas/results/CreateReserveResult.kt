package com.example.ezliv_mobile.ui.presentation.reservas.results

import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel

interface CreateReserveResult {
    data class Success(val data: Unit) : CreateReserveResult
    data class Error(val message: String) : CreateReserveResult
    object Loading : CreateReserveResult
}