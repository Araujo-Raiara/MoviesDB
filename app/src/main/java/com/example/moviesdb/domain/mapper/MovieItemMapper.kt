package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.core.yearMonthDayToYear
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.domain.model.MediaItem

fun MediaItemResponse.movieToMediaItem(): MediaItem {
    val posterImage = if(posterPath != null) {
        Constants.Network.IMAGE_BASE_URL + posterPath
    } else {
        null
    }
    val releaseYear = releaseDate?.yearMonthDayToYear()
    return MediaItem(
        id = id,
        title = title,
        releaseYear = releaseYear.orEmpty(),
        rating = rating?.toFloat(),
        posterImageUrl = posterImage,
        mediaType = "movie"
    )
}
