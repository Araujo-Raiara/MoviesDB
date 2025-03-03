package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.core.yearMonthDayToYear
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.domain.model.DetailsItem

fun MediaDetailResponse.toMovieDetailsItem(): DetailsItem {
    val backdropImageUrl = if (backDropPath != null) {
        Constants.Network.IMAGE_BASE_URL + backDropPath
    } else {
        null
    }
    val releaseYear = releaseDate?.yearMonthDayToYear()
    return DetailsItem(
        genre = genres?.map { it.name }?.joinToString().orEmpty(),
        title = title,
        releaseYear = releaseYear.orEmpty(),
        backdropImageUrl = backdropImageUrl,
        rating = rating?.toFloat(),
        runtime = runtime.toString(),
        description = overview.orEmpty()
    )
}