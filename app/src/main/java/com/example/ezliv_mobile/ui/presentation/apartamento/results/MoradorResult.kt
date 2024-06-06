package com.example.ezliv_mobile.ui.presentation.apartamento.results

import android.provider.ContactsContract.RawContacts.Data
import com.example.ezliv_mobile.ui.presentation.auth.result.NewPasswordResult

interface MoradorResult {
    data class Success(val data: Unit) : MoradorResult
    data class Error(val message: String?) : MoradorResult
    object Loading : MoradorResult
}