package com.example.moviesdb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesdb.domain.mapper.tvShowToMediaItem
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularTvShowsUseCase(
    private val repository: MoviesDBRepository
) {
    operator fun invoke(): Flow<PagingData<MediaItem>> {
        return repository.getPopularTvShow().map { pagingData ->
            pagingData.map { itemResponse ->
                itemResponse.tvShowToMediaItem()
            }
        }
    }
}
