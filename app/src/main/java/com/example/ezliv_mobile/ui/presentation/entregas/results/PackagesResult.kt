package com.example.ezliv_mobile.ui.presentation.entregas.results

import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel

interface PackagesResult {
    data class Success(val data: List<PackageModel>) : PackagesResult
    data class Error(val message: String) : PackagesResult
    object Loading : PackagesResult
}