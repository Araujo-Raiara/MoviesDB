package com.example.moviesdb.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesdb.R
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.ui.MoviesTabs
import com.example.moviesdb.ui.model.TabItem
import com.example.moviesdb.ui.navigation.NavigationItem
import com.example.moviesdb.ui.viewmodel.ListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: ListViewModel = koinViewModel()
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
            {
                ListContent(list = moviePopularList, triggerLoading = {
                    viewModel.getPopularMovies()
                }, onClick = { id, mediaType ->
                    navController.navigate(route = "${NavigationItem.Detail.route}/${id}/${mediaType}")
                })
            },
            {
                ListContent(list = tvShowPopularList, onClick = { id, mediaType ->
                    navController.navigate(route = "${NavigationItem.Detail.route}/${id}/${mediaType}")
                }, triggerLoading = {
                    viewModel.getPopularTvShows()
                }
                )
            }
        )
    )
}
