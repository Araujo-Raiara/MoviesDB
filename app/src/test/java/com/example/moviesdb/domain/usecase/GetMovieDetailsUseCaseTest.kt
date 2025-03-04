package com.example.moviesdb.domain.usecase

import com.example.moviesdb.core.Constants
import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.data.model.MovieGenre
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import com.example.moviesdb.ui.viewmodel.DetailsState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    private val repository: MoviesDBRepository = mockk()
    private val useCase = GetMovieDetailsUseCase(repository)

    @Test
    fun `GIVEN successful repository response WHEN invoke is called THEN returns success with mapped details`() =
        runTest {
            val mediaDetailResponse = MediaDetailResponse(
                title = "Test Movie",
                backDropPath = "/testBackdrop.jpg",
                posterPath = "/testPoster.jpg",
                releaseDate = "2023-01-01",
                rating = 8.5,
                runtime = 120,
                overview = "Test movie description",
                genres = listOf(MovieGenre(name = "Action"), MovieGenre(name = "Adventure"))
            )
            coEvery { repository.getMovieDetail(1) } returns DetailsState.Success(
                mediaDetailResponse
            )

            val result = useCase.invoke(1)

            val expected = DetailsState.Success(
                DetailsItem(
                    title = "Test Movie",
                    releaseYear = "2023",
                    backdropImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testBackdrop.jpg",
                    posterImageUrl = Constants.Network.IMAGE_BASE_URL_500dp + "/testPoster.jpg",
                    rating = 8.5,
                    runtime = 120,
                    overview = "Test movie description",
                    genre = listOf("Action", "Adventure")
                )
            )
            assertEquals(expected, result)
        }

    @Test
    fun `GIVEN repository returns error WHEN invoke is called THEN returns error state`() =
        runTest {
            val exception = Exception("Network error")
            coEvery { repository.getMovieDetail(1) } returns DetailsState.Error(exception)

            val result = useCase.invoke(1)

            val expected = DetailsState.Error<Exception>(exception)
            assertEquals(expected, result)
        }
}