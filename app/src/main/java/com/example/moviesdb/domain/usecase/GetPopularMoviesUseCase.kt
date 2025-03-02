package com.example.moviesdb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesdb.domain.mapper.movieToMediaItem
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularMoviesUseCase(
    private val repository: MoviesDBRepository
) {
    fun getPopularMovies(): Flow<PagingData<MediaItem>> {
        return repository.getPopularMovies().map { pagingData ->
            pagingData.map { movieItemResponse ->
                movieItemResponse.movieToMediaItem()
            }
        }
    }
}
