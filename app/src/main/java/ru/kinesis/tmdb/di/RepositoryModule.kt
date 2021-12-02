package ru.kinesis.tmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kinesis.tmdb.network.MovieService
import ru.kinesis.tmdb.network.model.MovieDtoMapper
import ru.kinesis.tmdb.repository.MovieRepository
import ru.kinesis.tmdb.repository.MovieRepository_Impl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
        movieDtoMapper: MovieDtoMapper
    ): MovieRepository{
        return MovieRepository_Impl(movieService, movieDtoMapper)
    }
}