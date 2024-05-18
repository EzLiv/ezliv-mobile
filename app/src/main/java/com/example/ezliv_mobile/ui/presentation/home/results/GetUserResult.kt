package com.example.ezliv_mobile.ui.presentation.home.results

import com.example.ezliv_mobile.ui.domain.home.models.UserModel

sealed interface GetUserResult {
    data class Success(val user : UserModel) : GetUserResult
    data class Error(val message: String?) : GetUserResult
    object Loading : GetUserResult
}