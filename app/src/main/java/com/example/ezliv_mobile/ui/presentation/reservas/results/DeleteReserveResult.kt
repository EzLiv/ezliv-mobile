package com.example.ezliv_mobile.ui.presentation.reservas.results

import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel

interface DeleteReserveResult {
    data class Success(val data: Unit) : DeleteReserveResult
    data class Error(val message: String) : DeleteReserveResult
    object Loading : DeleteReserveResult
}

