package ru.kinesis.tmdb.repository

import ru.kinesis.tmdb.domain.model.Movie

interface MovieRepository {

    suspend fun search(page: Int, query: String): List<Movie>

    suspend fun get (id: Int): Movie
}