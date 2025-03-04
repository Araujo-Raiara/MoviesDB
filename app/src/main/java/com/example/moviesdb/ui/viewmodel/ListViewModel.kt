package com.example.moviesdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesdb.domain.usecase.GetPopularTvShowsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<MediaItem>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: StateFlow<PagingData<MediaItem>> get() = _moviesState

    private val _tvShowState: MutableStateFlow<PagingData<MediaItem>> =
        MutableStateFlow(value = PagingData.empty())
    val tvShowState: StateFlow<PagingData<MediaItem>> get() = _tvShowState

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        getPopularMoviesUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

    fun getPopularTvShows() = viewModelScope.launch(Dispatchers.IO) {
        getPopularTvShowsUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _tvShowState.value = it
            }
    }
}
