package com.example.ezliv_mobile.ui.domain.morador.models

import java.util.Date

data class MoradorModel(
    val id: String,
    val fullName: String,
    val email: String,
    val cpf: String,
    val phone: String,
    val birthDate: Date,
    val gender: String,
    val isPrimaryResident: Boolean,
    val apartmentId: String,
    val condominiumId: String,
    val condominiumName: String
    )

//vai dar bigode no date voltar aqui//
