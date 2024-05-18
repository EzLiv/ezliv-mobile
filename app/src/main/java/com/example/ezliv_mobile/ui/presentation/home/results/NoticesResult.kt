package com.example.ezliv_mobile.ui.presentation.home.results

import com.example.ezliv_mobile.ui.domain.home.models.NoticeModel

sealed interface NoticesResult {
    data class Success(val data: List<NoticeModel>) : NoticesResult
    data class Error(val message: String) : NoticesResult
    object Loading : NoticesResult
}