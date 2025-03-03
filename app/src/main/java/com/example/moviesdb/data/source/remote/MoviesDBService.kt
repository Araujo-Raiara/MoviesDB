package com.example.moviesdb.data.source.remote

import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MoviesResponse

    @GET("tv/top_rated")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
    ): MoviesResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
    ): MediaDetailResponse

    @GET("tv/{tvShowId}")
    suspend fun getTvShowDetail(
        @Path("tvShowId") movieId: Int,
    ): MediaDetailResponse
}
