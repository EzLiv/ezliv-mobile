package com.example.ezliv_mobile.ui.presentation.reservas.results

import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel

interface GetCommonAreasResult {
    data class Success(val data: List<CommonAreaModel>) : GetCommonAreasResult
    data class Error(val message: String) : GetCommonAreasResult
    object Loading : GetCommonAreasResult
}