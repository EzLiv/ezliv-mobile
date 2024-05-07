package com.example.ezliv_mobile.ui.data.result

import com.example.ezliv_mobile.ui.data.models.UserModel

sealed interface GetUserResult {
    data class Success(val user : UserModel) : GetUserResult
    data class Error(val message: String?) : GetUserResult
    object Loading : GetUserResult
}