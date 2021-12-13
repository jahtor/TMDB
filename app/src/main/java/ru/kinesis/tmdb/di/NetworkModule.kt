package ru.kinesis.tmdb.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kinesis.tmdb.network.MovieService
import ru.kinesis.tmdb.network.model.MovieDtoMapper
import ru.kinesis.tmdb.util.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieMapper(): MovieDtoMapper{
        return MovieDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMovieService(): MovieService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(MovieService::class.java)
    }
}