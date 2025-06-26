package com.example.alfa.domain.repository

import com.example.alfa.domain.model.BinModel
import retrofit2.Response

interface BinRepository {
    suspend fun getBinInfo(bin: String): Response<BinModel>
    suspend fun saveBinToDatabase(model: BinModel, bin: String)
}