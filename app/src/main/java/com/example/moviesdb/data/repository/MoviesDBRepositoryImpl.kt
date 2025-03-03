package com.example.moviesdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.data.paging.PopularMoviePagingSource
import com.example.moviesdb.data.paging.PopularTvShowPagingSource
import com.example.moviesdb.data.source.remote.MoviesDBService
import com.example.moviesdb.domain.repository.MoviesDBRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

private val pagingConfig = PagingConfig(
    pageSize = 1,
    prefetchDistance = 2
)

class MoviesDBRepositoryImpl(
    private val service: MoviesDBService,
) : MoviesDBRepository {
    override fun getPopularMovies(): Flow<PagingData<MediaItemResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PopularMoviePagingSource(service) }
        ).flow
    }

    override fun getPopularTvShow(): Flow<PagingData<MediaItemResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PopularTvShowPagingSource(service) }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MediaDetailResponse> {
        return fetchDetailData { service.getMovieDetail(movieId) }
    }

    override suspend fun getTvShowDetail(tvShowId: Int): Result<MediaDetailResponse> {
        return fetchDetailData { service.getTvShowDetail(tvShowId) }
    }

    private suspend fun fetchDetailData(call: suspend () -> MediaDetailResponse): Result<MediaDetailResponse> {
        return try {
            val result = call.invoke()
            Result.success(result)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}
