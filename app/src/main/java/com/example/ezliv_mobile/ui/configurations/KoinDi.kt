package com.example.ezliv_mobile.ui.configurations

import com.example.ezliv_mobile.ui.data.auth.remote.AuthRepository
import com.example.ezliv_mobile.ui.data.home.remote.HomeRepository
import com.example.ezliv_mobile.ui.presentation.home.view_model.HomeViewModel
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import com.example.ezliv_mobile.ui.presentation.auth.view_model.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<IAuthRepository> {
        AuthRepository()
    }
    factory<IHomeRepository> {
        HomeRepository()
    }
    viewModel {
        AuthViewModel(get(), androidContext())
    }
    viewModel {
        HomeViewModel(get(), androidContext())
    }

}