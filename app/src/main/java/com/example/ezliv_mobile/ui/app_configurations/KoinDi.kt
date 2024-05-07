package com.example.ezliv_mobile.ui.app_configurations

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezliv_mobile.ui.data.repo_impls.AuthRepository
import com.example.ezliv_mobile.ui.data.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.data.view_models.HomeViewModel
import com.example.ezliv_mobile.ui.data.view_models.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<IAuthRepository>{
        AuthRepository()
    }
    viewModel{
        UserViewModel(get(), androidContext())
    }
    viewModel{
        HomeViewModel(get(), androidContext())
    }

}