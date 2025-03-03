package com.example.moviesdb.core

import com.example.moviesdb.BuildConfig

object Constants {
    object Network {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val AUTHENTICATION_KEY = "Authorization"
        const val AUTHENTICATION_VALUE = "Bearer ${BuildConfig.API_KEY}"
        const val LANGUAGE_KEY = "language"
        const val LANGUAGE_VALUE = "pt-br"
    }

    object DefaultValues {
        const val EMPTY = ""
    }
}
