package com.example.moviesdb.domain.usecase

import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.domain.mapper.toMovieDetailsItem
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import com.example.moviesdb.ui.viewmodel.DetailsState

class GetMovieDetailsUseCase(
    private val repository: MoviesDBRepository
) {
    suspend operator fun invoke(movieId: Int): DetailsState<DetailsItem> {
        val response = repository.getMovieDetail(movieId)
        return if(response is DetailsState.Success<MediaDetailResponse>) {
            DetailsState.Success(response.content.toMovieDetailsItem())
        } else {
            DetailsState.Error((response as DetailsState.Error).exception)
        }
    }
}