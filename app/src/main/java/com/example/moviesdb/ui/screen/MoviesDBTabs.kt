package com.example.moviesdb.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesdb.R
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.ui.MoviesTabs
import com.example.moviesdb.ui.model.TabItem
import com.example.moviesdb.ui.theme.MoviesDBTheme
import com.example.moviesdb.ui.viewmodel.MoviesDBViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesDBScreen(modifier: Modifier = Modifier) {
    val viewModel: MoviesDBViewModel = koinViewModel()
    val moviePopularList: LazyPagingItems<MediaItem> =
        viewModel.moviesState.collectAsLazyPagingItems()
    val tvShowPopularList: LazyPagingItems<MediaItem> =
        viewModel.tvShowState.collectAsLazyPagingItems()

    MoviesTabs(
        modifier = modifier,
        tabs = listOf(
            TabItem(R.string.popular_movies, R.drawable.ic_movie),
            TabItem(R.string.popular_tv_shows, R.drawable.ic_tv)
        ),
        screens = listOf(
            { MovieListContent(list = moviePopularList) { viewModel.getPopularMovies() } },
            { MovieListContent(list = tvShowPopularList) { viewModel.getPopularTvShows() } },
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoviesDBTheme {
        MoviesDBScreen()
    }
}
