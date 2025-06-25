package com.example.alfa.domain.usecases

import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.repository.CardHistoryRepository
import kotlinx.coroutines.flow.Flow

class CardUseCase(private val repository: CardHistoryRepository) {
    fun invoke(): Flow<List<BinModel>> {
        return repository.getAll()
    }
}