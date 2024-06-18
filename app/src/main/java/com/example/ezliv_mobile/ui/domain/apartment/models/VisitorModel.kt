package com.example.ezliv_mobile.ui.domain.apartment.models

data class VisitorModel(
    val id: String,
    val name: String,
    val cpf: String,
    val email : String,
    val apartmentId: String,
    val releaseDate: String,
    val untilDate: String
)
