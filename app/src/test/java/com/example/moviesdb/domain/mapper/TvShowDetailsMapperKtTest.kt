package com.example.moviesdb.domain.mapper

import com.example.moviesdb.core.Constants
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MovieGenre
import com.example.moviesdb.domain.model.DetailsItem
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TvShowDetailsMapperKtTest {
    @Test
    fun `GIVEN MediaDetailResponse with all fields WHEN mapped to DetailsItem THEN returns expected TV Show DetailsItem`() {
        val response = MediaDetailResponse(
            name = "Test Show",
            backDropPath = "/testBackdrop.jpg",
            posterPath = "/testPoster.jpg",
            firstAirDate = "2022-05-10",
            rating = 7.9,
            runtime = 45,
            overview = "Test show description",
            genres = listOf(MovieGenre(name = "Comedy"), MovieGenre(name = "Drama"))
        )

        val result = response.toTvShowDetailsItem()

        val expected = DetailsItem(
            title = "Test Show",
            releaseYear = "2022",
            backdropImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testBackdrop.jpg",
            posterImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testPoster.jpg",
            rating = 7.9,
            runtime = 45,
            overview = "Test show description",
            genre = listOf("Comedy", "Drama")
        )
        assertEquals(expected, result)
    }

}