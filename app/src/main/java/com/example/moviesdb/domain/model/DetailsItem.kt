package com.example.moviesdb.domain.model

import com.example.moviesdb.core.Constants.DefaultValues.EMPTY

data class DetailsItem(
        val genre: String = EMPTY,
        val title: String? = null,
        val releaseYear: String? = null,
        val rating: Float? = null,
        val backdropImageUrl: String? = null,
        val runtime: String = EMPTY,
        val description: String = EMPTY,
        val isError: Boolean = false,
    )
