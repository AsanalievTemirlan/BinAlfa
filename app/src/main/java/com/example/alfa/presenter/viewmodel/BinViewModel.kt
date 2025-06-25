package com.example.alfa.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.usecases.GetBinInfoUseCase
import com.example.alfa.utills.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BinViewModel(private val useCase: GetBinInfoUseCase) : ViewModel() {

    private val _binInfo = MutableStateFlow<UiState<BinModel>>(UiState.Loading)
    val binInfo = _binInfo.asStateFlow()

    fun fetchBinInfo(bin: String) {
        viewModelScope.launch {
            _binInfo.value = UiState.Loading
            try {
                val response = useCase.invoke(bin)
                if (response.isSuccessful) {
                    _binInfo.value = UiState.Success(response.body()!!)
                } else {
                    _binInfo.value =
                        UiState.Error("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                _binInfo.value = UiState.Error("Exception: ${e.message}")
            }
        }
    }
}
