package com.example.moviesdb.domain.usecase

import com.example.moviesdb.data.model.MediaDetailResponse
import com.example.moviesdb.domain.mapper.toMovieDetailsItem
import com.example.moviesdb.domain.mapper.toTvShowDetailsItem
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.domain.repository.MoviesDBRepository
import com.example.moviesdb.ui.viewmodel.DetailsState

class GetTvShowDetailsUseCase(
    private val repository: MoviesDBRepository
) {
    suspend operator fun invoke(tvShowId: Int): DetailsState<DetailsItem> {
        val response = repository.getTvShowDetail(tvShowId)
        return if(response is DetailsState.Success<MediaDetailResponse>) {
            DetailsState.Success(response.content.toTvShowDetailsItem())
        } else {
            DetailsState.Error((response as DetailsState.Error).exception)
        }
    }
}