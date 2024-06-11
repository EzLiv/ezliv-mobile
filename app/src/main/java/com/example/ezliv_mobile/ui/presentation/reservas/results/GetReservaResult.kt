package com.example.ezliv_mobile.ui.presentation.reservas.results

import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel
import com.example.ezliv_mobile.ui.domain.reserva.models.ReservaModel
import com.example.ezliv_mobile.ui.presentation.home.results.NoticesResult

interface GetReservaResult {
    data class Success(val data: List<ReservaModel>) : GetReservaResult
    data class Error(val message: String) : GetReservaResult
    object Loading : GetReservaResult
}