package com.example.ezliv_mobile.ui.presentation.apartamento.results

import com.example.ezliv_mobile.ui.domain.apartment.models.Resident

interface UpdateResident {
    data class Success(val data: Unit) : UpdateResident
    data class Error(val message: String) : UpdateResident
    object Loading : UpdateResident
}