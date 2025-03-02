package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.core.yearMonthDayToYear
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.domain.model.MediaItem

fun MediaItemResponse.tvShowToMediaItem(): MediaItem {
    val posterImage = if (posterPath != null) {
        Constants.Network.IMAGE_BASE_URL + posterPath
    } else {
        null
    }
    val releaseYear = firstAirDate?.yearMonthDayToYear()
    return MediaItem(
        id = id,
        title = name,
        releaseYear = releaseYear,
        rating = rating?.toFloat(),
        posterImageUrl = posterImage,
    )
}
