package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.model.MediaType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TvShowItemMapperKtTest {
    @Test
    fun `GIVEN MediaItemResponse with all fields WHEN mapped to MediaItem THEN returns expected TV MediaItem`() {
        val response = MediaItemResponse(
            id = 1,
            name = "Test Show",
            posterPath = "/testPoster.jpg",
            firstAirDate = "2023-01-01",
            rating = 8.5
        )

        val result = response.tvShowToMediaItem()

        val expected = MediaItem(
            id = 1,
            title = "Test Show",
            releaseYear = "2023",
            rating = 8.5,
            posterImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testPoster.jpg",
            mediaType = MediaType.TV
        )
        assertEquals(expected, result)
    }
}