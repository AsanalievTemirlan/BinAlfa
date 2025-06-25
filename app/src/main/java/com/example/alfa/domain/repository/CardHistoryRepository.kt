package com.example.alfa.domain.repository

import com.example.alfa.domain.model.BinModel
import kotlinx.coroutines.flow.Flow

interface CardHistoryRepository {
    fun getAll(): Flow<List<BinModel>>
}