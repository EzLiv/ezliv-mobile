package com.example.ezliv_mobile.ui.domain.visitante.models

import java.util.Date

data class VisitanteModel (
    val id: String,
    val name: String,
    val email: String,
    val cpf: String,
    val apartmentId: String,
    val releaseDate: Date,
    val untilDate: Date
)