package com.example.moviesdb.data.model

import com.squareup.moshi.Json

data class MediaDetailResponse(
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    val name: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    @Json(name = "first_air_date")
    val firstAirDate: String? = null,
    val runtime: Int? = null,
    @Json(name = "vote_average")
    val rating: Double? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    val overview: String? = null,
    @Json(name = "backdrop_path")
    val backDropPath: String? = null
)
