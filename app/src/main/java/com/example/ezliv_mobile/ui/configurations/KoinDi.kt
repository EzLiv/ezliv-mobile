package com.example.ezliv_mobile.ui.configurations

import com.example.ezliv_mobile.ui.data.apartment.remote.ApartmentRepository
import com.example.ezliv_mobile.ui.data.auth.remote.AuthRepository
import com.example.ezliv_mobile.ui.data.entregas.remote.EntregasRepository
import com.example.ezliv_mobile.ui.data.home.remote.HomeRepository
import com.example.ezliv_mobile.ui.data.reserves.remote.ReserveRepository
import com.example.ezliv_mobile.ui.domain.apartment.repositories.IApartmentRepository
import com.example.ezliv_mobile.ui.presentation.home.view_model.HomeViewModel
import com.example.ezliv_mobile.ui.domain.auth.repositories.IAuthRepository
import com.example.ezliv_mobile.ui.domain.entregas.repositories.IEntregasRepository
import com.example.ezliv_mobile.ui.domain.home.repositories.IHomeRepository
import com.example.ezliv_mobile.ui.domain.reserves.repositories.IReserveRepository
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.auth.view_model.AuthViewModel
import com.example.ezliv_mobile.ui.presentation.entregas.view_model.EntregasViewModel
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReserveViewModel
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
    factory<IApartmentRepository> {
        ApartmentRepository()
    }
    factory<IReserveRepository> {
        ReserveRepository()
    }
    viewModel {
        AuthViewModel(get(), androidContext())
    }
    viewModel {
        HomeViewModel(get(), androidContext())
    }
    factory<IEntregasRepository> {
        EntregasRepository()
    }
    viewModel {
        EntregasViewModel(get(), androidContext())
    }
    viewModel {
        ApartmentViewModel(get(), androidContext())
    }
    viewModel {
        ReserveViewModel(get(), androidContext())
    }
}