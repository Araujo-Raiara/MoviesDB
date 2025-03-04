package com.example.moviesdb.domain.usecase

import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.domain.mapper.toTvShowDetailsItem
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import com.example.moviesdb.ui.viewmodel.DetailsState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTvShowDetailsUseCaseTest {
    private val repository: MoviesDBRepository = mockk()
    private val useCase = GetTvShowDetailsUseCase(repository)

    @Test
    fun `GIVEN valid tvShowId when invoke is called then return success with tvShow details`() =
        runTest {
            val mediaDetailResponse = mockk<MediaDetailResponse>()
            val tvShowDetailsItem = mockk<DetailsItem>()
            mockkStatic(MediaDetailResponse::toTvShowDetailsItem)
            val successResponse = DetailsState.Success(mediaDetailResponse)
            coEvery { repository.getTvShowDetail(any()) } returns successResponse
            every { any<MediaDetailResponse>().toTvShowDetailsItem() } returns tvShowDetailsItem

            val result = useCase(123)

            assertTrue(result is DetailsState.Success)
            assertEquals(DetailsState.Success(tvShowDetailsItem), result)
            coVerify { repository.getTvShowDetail(123) }
            verify { mediaDetailResponse.toTvShowDetailsItem() }
            unmockkStatic(MediaDetailResponse::toTvShowDetailsItem)
        }

    @Test
    fun `GIVEN error repository response when invoke is called then return error with exception`() =
        runTest {
            val repositoryResponse = DetailsState.Error<MediaDetailResponse>(NullPointerException())
            coEvery { repository.getTvShowDetail(any()) } returns repositoryResponse

            val result = useCase(123)

            assert(result is DetailsState.Error<DetailsItem>)
            assert((result as DetailsState.Error).exception is NullPointerException)
        }
}