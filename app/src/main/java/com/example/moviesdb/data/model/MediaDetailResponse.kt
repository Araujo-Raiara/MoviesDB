package com.example.moviesdb.data.model

import com.google.gson.annotations.SerializedName

data class MediaDetailResponse(
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val runtime: Int? = null,
    @SerializedName("vote_average")
    val rating: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val overview: String? = null,
)
