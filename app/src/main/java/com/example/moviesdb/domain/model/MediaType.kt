package com.example.moviesdb.domain.model

enum class MediaType {
    MOVIE, TV;

    companion object {
        fun fromName(name: String) : MediaType = entries.firstOrNull { it.name == name } ?: MOVIE
    }
}