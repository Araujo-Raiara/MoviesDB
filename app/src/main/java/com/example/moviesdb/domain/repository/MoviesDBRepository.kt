package com.example.moviesdb.domain.repository

import androidx.paging.PagingData
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MediaItemResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDBRepository {
    fun getPopularMovies(): Flow<PagingData<MediaItemResponse>>
    fun getPopularTvShow(): Flow<PagingData<MediaItemResponse>>
    suspend fun getMovieDetail(movieId: Int): Result<MediaDetailResponse>
    suspend fun getTvShowDetail(tvShowId: Int): Result<MediaDetailResponse>
}
