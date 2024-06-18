package com.example.ezliv_mobile.ui.presentation.bills.result

import com.example.ezliv_mobile.ui.domain.bills.models.BillModel
import com.example.ezliv_mobile.ui.domain.home.models.UserModel
import com.example.ezliv_mobile.ui.presentation.home.results.GetUserResult

interface BillsResult {
    data class Success(val data : List<BillModel>) : BillsResult
    data class Error(val message: String?) : BillsResult
    object Loading : BillsResult
}