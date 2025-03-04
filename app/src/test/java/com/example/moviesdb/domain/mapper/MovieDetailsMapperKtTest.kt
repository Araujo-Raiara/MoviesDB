package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MovieGenre
import com.example.moviesdb.domain.model.DetailsItem
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsMapperKtTest {

    @Test
    fun `GIVEN MediaDetailResponse with all fields WHEN mapped to DetailsItem THEN returns expected DetailsItem`() {
        val response = MediaDetailResponse(
            title = "Test Movie",
            backDropPath = "/testBackdrop.jpg",
            posterPath = "/testPoster.jpg",
            releaseDate = "2023-01-01",
            rating = 8.5,
            runtime = 120,
            overview = "Test description",
            genres = listOf(MovieGenre(name = "Action"), MovieGenre(name = "Drama"))
        )

        val result = response.toMovieDetailsItem()

        val expected = DetailsItem(
            title = "Test Movie",
            releaseYear = "2023",
            backdropImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testBackdrop.jpg",
            posterImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testPoster.jpg",
            rating = 8.5,
            runtime = 120,
            overview = "Test description",
            genre = listOf("Action", "Drama")
        )
        assertEquals(expected, result)
    }
}
