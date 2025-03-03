package com.example.moviesdb.ui.navigation

enum class MovieDBScreens {
    LIST,
    DETAIL
}
sealed class NavigationItem(val route: String) {
    data object List : NavigationItem(MovieDBScreens.LIST.name)
    data object Detail : NavigationItem(MovieDBScreens.DETAIL.name)
}