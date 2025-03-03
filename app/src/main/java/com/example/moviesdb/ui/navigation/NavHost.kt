package com.example.moviesdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviesdb.core.orZero
import com.example.moviesdb.ui.screen.DetailsScreen
import com.example.moviesdb.ui.screen.ListScreen

private const val ID = "id"
private const val MEDIA_TYPE = "mediaType"

    @Composable
    fun MovieDBNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = NavigationItem.List.route
        ) {
            composable(NavigationItem.List.route) {
                ListScreen(
                    navController = navController
                )
            }
            composable("${NavigationItem.Detail.route}/{${ID}}/{${MEDIA_TYPE}}",
                arguments = listOf(navArgument(ID) {
                    type = NavType.IntType
                },
                    navArgument(MEDIA_TYPE) {
                        type = NavType.StringType
                    }
                )) { backStackEntry ->
                val mediaId = backStackEntry.arguments?.getInt(ID).orZero()
                val mediaType = backStackEntry.arguments?.getString(MEDIA_TYPE).orEmpty()
                DetailsScreen(
                    mediaId = mediaId,
                    mediaType = mediaType,
                    navController = navController
                )
            }
        }
    }