package com.example.ezliv_mobile.ui.data.result

import com.example.ezliv_mobile.ui.data.models.NoticeModel

sealed interface NoticesResult {
    data class Success(val data: List<NoticeModel>) : NoticesResult
    data class Error(val message: String) : NoticesResult
    object Loading : NoticesResult
}