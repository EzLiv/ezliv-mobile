package com.example.ezliv_mobile.ui.domain.entregas.models

data class PackageModel(
    val id: String,
    val code: String,
    val description: String,
    val condominiumId: String,
    val apartmentId: String,
    val delivered: Boolean,
    val receiptDate: String,
    val deliveryDate: String?,
    val apartmentName: String,
    val towerName: String,
)