package com.example.moviesdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.domain.usecase.GetMovieDetailsUseCase
import com.example.moviesdb.domain.usecase.GetTvShowDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
) : ViewModel() {

    private val _mediaDetailsState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val mediaDetailsState: StateFlow<DetailsUiState> get() = _mediaDetailsState

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _mediaDetailsState.value = DetailsUiState.Loading
        val result = getMovieDetailsUseCase(movieId)
        _mediaDetailsState.value = result.fold(
            onSuccess = { details -> DetailsUiState.Success(details) },
            onFailure = { throwable ->
                DetailsUiState.Error(
                    throwable.message ?: GENERIC_ERROR_MESSAGE
                )
            }
        )
    }

    private fun getTvShowDetails(tvShowId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _mediaDetailsState.value = DetailsUiState.Loading
        val result = getTvShowDetailsUseCase(tvShowId)
        _mediaDetailsState.value = result.fold(
            onSuccess = { details -> DetailsUiState.Success(details) },
            onFailure = { throwable ->
                DetailsUiState.Error(
                    throwable.message ?: GENERIC_ERROR_MESSAGE
                )
            }
        )
    }

    fun setupDetails(id: Int, mediaType: String) {
        if (mediaType == "movie") {
            getMovieDetails(id)
        } else {
            getTvShowDetails(id)
        }
    }

    private companion object {
        const val GENERIC_ERROR_MESSAGE = "Unknown error"
    }
}
