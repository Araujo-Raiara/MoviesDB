package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.mapper.toMovieDetailsItem
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository

class GetMovieDetailsUseCase(
    private val repository: MoviesDBRepository
) {
    suspend operator fun invoke(movieId: Int): Result<DetailsItem> {
        val response = repository.getMovieDetail(movieId)
        return if(response.isSuccess) {
            response.map { it.toMovieDetailsItem() }
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}