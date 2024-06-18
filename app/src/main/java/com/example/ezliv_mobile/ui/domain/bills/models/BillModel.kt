package com.example.ezliv_mobile.ui.domain.bills.models

data class BillModel(
    val amount: Double,
    val invoiceStatus: String,
    val archiveUrl: String,
    val billDueDate: String,
    val monthReference: String,
    val barCode : String
)
