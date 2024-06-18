package com.example.ezliv_mobile.ui.presentation.reservas.results

import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel

interface GetReservesResult {
    data class Success(val data: List<ReserveModel>) : GetReservesResult
    data class Error(val message: String) : GetReservesResult
    object Loading : GetReservesResult
}