package com.example.ezliv_mobile.ui.models

data class Token(
    val token: String,
    val role: String,
    val firstLoginAlreadyDone : Boolean,
    val id : String
)
