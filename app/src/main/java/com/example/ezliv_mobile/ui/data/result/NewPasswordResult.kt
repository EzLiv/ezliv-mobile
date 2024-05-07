package com.example.ezliv_mobile.ui.data.result

sealed interface NewPasswordResult {
    object Success : NewPasswordResult
    data class Error(val message: String?) : NewPasswordResult
    object Loading : NewPasswordResult
}