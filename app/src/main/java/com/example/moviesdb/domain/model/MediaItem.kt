package com.example.moviesdb.domain.model

data class MediaItem(
    val id: Int?,
    val title: String?,
    val releaseYear: String?,
    val rating: Double?,
    val posterImageUrl: String?,
    val mediaType: MediaType,
)
