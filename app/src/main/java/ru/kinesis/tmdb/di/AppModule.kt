package ru.kinesis.tmdb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kinesis.tmdb.presentation.MovieApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MovieApplication {
        return app as MovieApplication
    }

    @Singleton
    @Provides
    fun ramdomString(): String {
        return "AppModule Random_Injection!"
    }
}