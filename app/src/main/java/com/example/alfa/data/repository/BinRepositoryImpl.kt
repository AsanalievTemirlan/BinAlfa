package com.example.alfa.data.repository

import com.example.alfa.data.local.dao.BinDao
import com.example.alfa.data.local.entity.toBinEntity
import com.example.alfa.data.network.api.BinApiService
import com.example.alfa.data.network.dto.toBinModel
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.repository.BinRepository
import com.example.alfa.utills.LogUtil
import retrofit2.Response

class BinRepositoryImpl(private val binApiService: BinApiService, private val dao: BinDao) :
    BinRepository {
    override suspend fun getBinInfo(bin: String): Response<BinModel> {
        val response = binApiService.getBinInfo(bin)
        return if (response.isSuccessful) {
            val binModel = response.body()?.toBinModel()
            Response.success(binModel)
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun saveBinToDatabase(model: BinModel) =
        dao.addBin(model.toBinEntity())

}
