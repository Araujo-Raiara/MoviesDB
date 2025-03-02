package com.example.moviesdb.data.model

data class MoviesResponse(
    val page: Int,
    val results: List<MediaItemResponse>
)
