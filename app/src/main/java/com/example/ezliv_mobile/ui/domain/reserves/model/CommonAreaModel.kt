package com.example.ezliv_mobile.ui.domain.reserves.model

data class CommonAreaModel(
    val id: String,
    val name: String,
    val openingTime: String,
    val closingTime: String,
    val maxReservationTime: String,
    val minReservationTime: String
)
