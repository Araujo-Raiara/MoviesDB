package com.example.moviesdb.ui.viewmodel

import com.example.moviesdb.domain.model.DetailsItem

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val detailsContent: DetailsItem) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}
