package com.example.ezliv_mobile.ui.data.entregas.remote

import com.example.ezliv_mobile.ui.data.provider.Api
import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import com.example.ezliv_mobile.ui.domain.entregas.repositories.IEntregasRepository
import retrofit2.Response

class EntregasRepository : IEntregasRepository {

    private val api by lazy {
        Api.getInstance().create(EntregasService::class.java)
    }


    override suspend fun getByApartmentId(condominiumId: String, apartmentId: String): Response<List<PackageModel>> {
        return api.getPackageByApartment(condominiumId, apartmentId)
    }


}
