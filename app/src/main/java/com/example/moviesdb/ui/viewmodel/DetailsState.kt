package com.example.moviesdb.ui.viewmodel

sealed class DetailsState<in T> {
    data object Loading : DetailsState<Any>()
    data class Success<T>(val content: T) : DetailsState<T>()
    data class Error<T>(val exception: Exception) : DetailsState<T>()
}