package com.example.alfa.utills

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
}