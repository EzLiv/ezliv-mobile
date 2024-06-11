package com.example.ezliv_mobile.ui.domain.entregas.repositories

import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import retrofit2.Response

interface IEntregasRepository{

    suspend fun getByApartmentId(condominiumId: String, apartmentId: String) : Response<List<PackageModel>>
}