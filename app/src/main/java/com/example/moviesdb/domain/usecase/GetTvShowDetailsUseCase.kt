package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.mapper.toTvShowDetailsItem
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository

class GetTvShowDetailsUseCase(
    private val repository: MoviesDBRepository
) {
    suspend operator fun invoke(tvShowId: Int): Result<DetailsItem> {
        val response = repository.getTvShowDetail(tvShowId)
        return if(response.isSuccess) {
            response.map { it.toTvShowDetailsItem() }
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}