package com.example.ezliv_mobile.ui.configurations

import com.example.ezliv_mobile.ui.data.auth.remote.AuthRepository
import com.example.ezliv_mobile.ui.data.home.remote.HomeRepository
import com.example.ezliv_mobile.ui.data.morador.remote.MoradorRepository
import com.example.ezliv_mobile.ui.data.visitante.remote.VisitanteRepository
import com.example.ezliv_mobile.ui.presentation.home.view_model.HomeViewModel
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import com.example.ezliv_mobile.ui.domain.morador.repositories.IMoradorRepository
import com.example.ezliv_mobile.ui.domain.visitante.repositories.IVisitanteRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.MoradorViewModel
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
    factory<IMoradorRepository> {
        MoradorRepository()
    }
    factory<IVisitanteRepository> {
       VisitanteRepository()
    }
    viewModel {
        AuthViewModel(get(), androidContext())
    }
    viewModel {
        HomeViewModel(get(), androidContext())
    }
    viewModel{
        MoradorViewModel(get(), androidContext())
    }

}