package com.example.alfa.domain.usecases

import com.example.alfa.data.local.dao.BinDao
import com.example.alfa.data.local.entity.toBinEntity
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.repository.BinRepository
import com.example.alfa.utills.LogUtil
import retrofit2.Response

class GetBinInfoUseCase(private val repository: BinRepository) {
    suspend fun invoke(bin: String): Response<BinModel> {
        val response = repository.getBinInfo(bin)
        if (response.isSuccessful) {
            response.body()?.let {
                repository.saveBinToDatabase(it)
            } ?: run {
                LogUtil.e("Response body is null")
            }
        } else {
            LogUtil.e("GetBinInfoUseCase error: ${response.code()}, ${response.message()}")
        }
        return response
    }
}
