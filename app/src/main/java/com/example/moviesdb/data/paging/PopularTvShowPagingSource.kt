package com.example.moviesdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.data.source.remote.MoviesDBService

class PopularTvShowPagingSource(
    private val service: MoviesDBService,
) : PagingSource<Int, MediaItemResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MediaItemResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaItemResponse> {
        return networkCallToResultObject(params) { service.getPopularTvShows(it) }
    }
}
