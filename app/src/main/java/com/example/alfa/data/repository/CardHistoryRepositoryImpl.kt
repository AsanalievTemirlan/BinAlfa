package com.example.alfa.data.repository

import com.example.alfa.data.local.dao.BinDao
import com.example.alfa.data.local.entity.toBinModel
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.repository.CardHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardHistoryRepositoryImpl(private val dao: BinDao): CardHistoryRepository {
    override fun getAll(): Flow<List<BinModel>> {
        return dao.getAllCard().map {
            it.map { binEntity ->
                binEntity.toBinModel()
            }
        }
    }
}