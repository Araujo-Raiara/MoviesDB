package com.example.moviesdb.domain.model

data class DetailsItem(
    val genre: List<String>? = null,
    val title: String? = null,
    val releaseYear: String? = null,
    val rating: Double? = null,
    val backdropImageUrl: String? = null,
    val posterImageUrl: String? = null,
    val runtime: Int? = null,
    val overview: String? = null,
    val isError: Boolean = false,
)
