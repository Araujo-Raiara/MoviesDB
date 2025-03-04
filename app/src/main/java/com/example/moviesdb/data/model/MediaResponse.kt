package com.example.moviesdb.data.model

data class MediaResponse(
    val page: Int,
    val results: List<MediaItemResponse>
)
