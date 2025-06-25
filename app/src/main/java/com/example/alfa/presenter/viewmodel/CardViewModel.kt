package com.example.alfa.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.usecases.CardUseCase
import com.example.alfa.utills.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardViewModel(private val cardUseCase: CardUseCase) : ViewModel() {

    private val _card = MutableStateFlow<UiState<List<BinModel>>>(UiState.Loading)
    val card = _card.asStateFlow()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            try {
                cardUseCase.invoke().collect {
                    _card.value = UiState.Success(it)
                }
            } catch (e: Exception) {
                _card.value = UiState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}