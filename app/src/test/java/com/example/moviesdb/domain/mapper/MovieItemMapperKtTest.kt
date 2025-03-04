package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.model.MediaType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieItemMapperKtTest {

    @Test
    fun `GIVEN MediaItemResponse with all fields WHEN mapped to MediaItem THEN returns expected MediaItem`() {
        val response = MediaItemResponse(
            id = 1,
            title = "Test Movie",
            posterPath = "/testPoster.jpg",
            releaseDate = "2023-01-01",
            rating = 8.5
        )

        val result = response.movieToMediaItem()

        val expected = MediaItem(
            id = 1,
            title = "Test Movie",
            releaseYear = "2023",
            rating = 8.5,
            posterImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testPoster.jpg",
            mediaType = MediaType.MOVIE
        )
        assertEquals(expected, result)
    }
}
