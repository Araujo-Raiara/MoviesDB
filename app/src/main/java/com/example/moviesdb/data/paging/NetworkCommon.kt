package com.example.moviesdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import com.example.moviesdb.core.orOne
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.data.model.MoviesResponse
import retrofit2.HttpException
import java.io.IOException

private const val FIRST_POSSIBLE_KEY = 1
private const val INCREMENT = 1

suspend fun networkCallToResultObject(
    params: PagingSource.LoadParams<Int>,
    networkCall: suspend (page: Int) -> MoviesResponse
): LoadResult<Int, MediaItemResponse> {
    try {
        val currentPage = params.key.orOne()
        val media = networkCall.invoke(currentPage)
        return LoadResult.Page(
            data = media.results,
            prevKey = if (currentPage == FIRST_POSSIBLE_KEY) null else media.page.orOne(),
            nextKey = if (media.results.isEmpty()) null else media.page + INCREMENT
        )
    } catch (e: IOException) {
        return LoadResult.Error(e)
    } catch (e: HttpException) {
        return LoadResult.Error(e)
    }
}
