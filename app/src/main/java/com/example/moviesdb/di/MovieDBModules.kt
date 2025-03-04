package com.example.moviesdb.di

import com.example.moviesdb.core.Constants.Network.BASE_URL
import com.example.moviesdb.data.paging.PopularMoviePagingSource
import com.example.moviesdb.data.paging.PopularTvShowPagingSource
import com.example.moviesdb.data.repository.MoviesDBRepositoryImpl
import com.example.moviesdb.data.source.remote.MoviesDBService
import com.example.moviesdb.domain.repository.MoviesDBRepository
import com.example.moviesdb.domain.usecase.GetMovieDetailsUseCase
import com.example.moviesdb.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesdb.domain.usecase.GetPopularTvShowsUseCase
import com.example.moviesdb.domain.usecase.GetTvShowDetailsUseCase
import com.example.moviesdb.ui.viewmodel.DetailsViewModel
import com.example.moviesdb.ui.viewmodel.ListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val moviesDBModules = module {
    single { providesRetrofit() }
    single { providesService(get()) }
    single { PopularTvShowPagingSource(get()) }
    single { PopularMoviePagingSource(get()) }
    single<MoviesDBRepository> { MoviesDBRepositoryImpl(get(), get(), get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetPopularTvShowsUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { GetTvShowDetailsUseCase(get()) }
    viewModel { ListViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
}

fun providesRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient: OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(MovieApiInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}

fun providesService(retrofit: Retrofit): MoviesDBService {
    return retrofit.create(MoviesDBService::class.java)
}
