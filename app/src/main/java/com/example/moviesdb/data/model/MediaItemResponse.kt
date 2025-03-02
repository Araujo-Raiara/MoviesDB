package com.example.moviesdb.data.model

import com.squareup.moshi.Json

data class MediaItemResponse(
    val id: Int? = null,
    val title: String? = null,
    val name: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    @Json(name = "first_air_date")
    val firstAirDate: String? = null,
    @Json(name = "vote_average")
    val rating: Double? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
)
