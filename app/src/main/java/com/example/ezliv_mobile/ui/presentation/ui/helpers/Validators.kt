package com.example.ezliv_mobile.ui.presentation.ui.helpers

fun isValidName(name: String): Boolean {
    return name.isNotBlank()
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    return emailRegex.matches(email)
}

fun isValidPhone(phone: String): Boolean {
    val phoneRegex = Regex("^\\(?(\\d{2})\\)?[-.\\s]?((?:9\\d|[2-9])\\d{3})[-.\\s]?(\\d{4})\$")
    return phoneRegex.matches(phone)
}