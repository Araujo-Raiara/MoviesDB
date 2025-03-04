package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.core.yearMonthDayToYear
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.domain.model.DetailsItem

fun MediaDetailResponse.toMovieDetailsItem(): DetailsItem {
    val backdropImageUrl = if (backDropPath != null) {
        Constants.Network.IMAGE_BASE_URL_500dp + backDropPath
    } else {
        null
    }
    val posterImage = if (posterPath != null) {
        Constants.Network.IMAGE_BASE_URL_500dp + posterPath
    } else {
        null
    }
    val releaseYear = releaseDate?.yearMonthDayToYear()
    return DetailsItem(
        genre = genres?.mapNotNull { it.name },
        title = title,
        releaseYear = releaseYear,
        backdropImageUrl = backdropImageUrl,
        posterImageUrl = posterImage,
        rating = rating,
        runtime = runtime,
        overview = overview
    )
}