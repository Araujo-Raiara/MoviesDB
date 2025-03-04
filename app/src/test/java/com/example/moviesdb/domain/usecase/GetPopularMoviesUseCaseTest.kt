package com.example.moviesdb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.example.moviesdb.data.model.MediaItemResponse
import com.example.moviesdb.domain.mapper.movieToMediaItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private val repository: MoviesDBRepository = mockk()
    private val useCase = GetPopularMoviesUseCase(repository)

    @Test
    fun `GIVEN popular movies when getPopularMovies is called then return mapped media items`() =
        runTest {
            val repositoryResponse = flowOf(PagingData.from(listOf(mockMediaItemResponse)))
            every { repository.getPopularMovies() } returns repositoryResponse
            val mockedMediaItem = mockMediaItemResponse.movieToMediaItem()
            val expected = flowOf(PagingData.from(listOf(mockedMediaItem)))

            val response = useCase()

            assertEquals(expected.asSnapshot(), response.asSnapshot())
        }
}

private val mockMediaItemResponse = MediaItemResponse(
    id = 123,
    title = "title",
    name = "name",
    releaseDate = "2024-12-12",
    firstAirDate = "2012-12-12",
    rating = 4.124,
    posterPath = "image.jpg"
)