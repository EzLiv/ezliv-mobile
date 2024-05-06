package com.example.ezliv_mobile.ui

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezliv_mobile.ui.data.repo_impls.AuthRepository
import com.example.ezliv_mobile.ui.data.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.data.view_models.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IAuthRepository>{
        AuthRepository()
    }
    viewModel{
        UserViewModel(get())
    }
}