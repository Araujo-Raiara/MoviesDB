package com.example.moviesdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.model.MediaType
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

    private val _mediaDetailsState: MutableStateFlow<DetailsState<DetailsItem>> =
        MutableStateFlow(DetailsState.Loading)
    val mediaDetailsState: StateFlow<DetailsState<DetailsItem>> get() = _mediaDetailsState

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _mediaDetailsState.value = DetailsState.Loading
        val result = getMovieDetailsUseCase(movieId)
        _mediaDetailsState.value = result
    }

    private fun getTvShowDetails(tvShowId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _mediaDetailsState.value = DetailsState.Loading
        val result = getTvShowDetailsUseCase(tvShowId)
        _mediaDetailsState.value = result
    }

    fun setupDetails(id: Int, mediaType: MediaType) {
        when(mediaType) {
            MediaType.MOVIE -> getMovieDetails(id)
            MediaType.TV -> getTvShowDetails(id)
        }
    }
}
