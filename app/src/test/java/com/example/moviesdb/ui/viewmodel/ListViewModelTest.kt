import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.model.MediaType
import com.example.moviesdb.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesdb.domain.usecase.GetPopularTvShowsUseCase
import com.example.moviesdb.ui.viewmodel.ListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ListViewModelTest {

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase = mockk()

    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(getPopularMoviesUseCase, getPopularTvShowsUseCase)
    }

    @Test
    fun `getting popular movies should emit empty list then list of objects`() =
        runTest {
            val popularMoviesResponse = flowOf(PagingData.from(listOf(mediaItem)))
            val popularTvShowsResponse = flowOf(
                PagingData.from(listOf(mediaItemTv))
            )

            coEvery { getPopularMoviesUseCase() } returns popularMoviesResponse
            coEvery { getPopularTvShowsUseCase() } returns popularTvShowsResponse

            viewModel.getPopularMovies()

            viewModel.moviesState.test {
                assertEquals(
                    flowOf(PagingData.from(listOf())).asSnapshot(),
                    flowOf(awaitItem()).asSnapshot()
                )
                assertEquals(
                    listOf(mediaItem),
                    flowOf(awaitItem()).asSnapshot()
                )
            }
        }

    @Test
    fun `getting popular tv shows should emit empty list then list of objects`() =
        runTest {
            val popularTvShowsResponse = flowOf(
                PagingData.from(listOf(mediaItemTv))
            )

            coEvery { getPopularTvShowsUseCase() } returns popularTvShowsResponse

            viewModel.getPopularTvShows()

            viewModel.tvShowState.test {
                assertEquals(
                    flowOf(PagingData.from(listOf())).asSnapshot(),
                    flowOf(awaitItem()).asSnapshot()
                )
                assertEquals(
                    listOf(mediaItem),
                    flowOf(awaitItem()).asSnapshot()
                )
            }
        }
}

private val mediaItem = MediaItem(
    title = "Test Movie 1",
    posterImageUrl = "/testMovie1.jpg",
    id = 123,
    releaseYear = "year",
    rating = 9.0,
    mediaType = MediaType.MOVIE
)
private val mediaItemTv = MediaItem(
    title = "Test Movie 1",
    posterImageUrl = "/testMovie1.jpg",
    id = 123,
    releaseYear = "year",
    rating = 9.0,
    mediaType = MediaType.TV
)