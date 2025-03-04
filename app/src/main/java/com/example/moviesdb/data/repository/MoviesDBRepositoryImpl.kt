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
import com.example.moviesdb.ui.viewmodel.DetailsState
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

private val pagingConfig = PagingConfig(
    pageSize = 20,
    prefetchDistance = 4
)

class MoviesDBRepositoryImpl(
    private val service: MoviesDBService,
    private val moviePagingSource: PopularMoviePagingSource,
    private val tvPagingSource: PopularTvShowPagingSource
) : MoviesDBRepository {
    override fun getPopularMovies(): Flow<PagingData<MediaItemResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { moviePagingSource }
        ).flow
    }

    override fun getPopularTvShow(): Flow<PagingData<MediaItemResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { tvPagingSource }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): DetailsState<MediaDetailResponse> {
        return fetchDetailData { service.getMovieDetail(movieId) }
    }

    override suspend fun getTvShowDetail(tvShowId: Int): DetailsState<MediaDetailResponse> {
        return fetchDetailData { service.getTvShowDetail(tvShowId) }
    }

    private suspend fun fetchDetailData(call: suspend () -> MediaDetailResponse): DetailsState<MediaDetailResponse> {
        return try {
            val result = call.invoke()
            DetailsState.Success(result)
        } catch (e: IOException) {
            DetailsState.Error(e)
        } catch (e: HttpException) {
            DetailsState.Error(e)
        }
    }
}
