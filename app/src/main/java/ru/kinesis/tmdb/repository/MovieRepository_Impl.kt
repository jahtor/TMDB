package ru.kinesis.tmdb.repository

import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.network.MovieService
import ru.kinesis.tmdb.network.model.MovieDtoMapper

class MovieRepository_Impl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper
): MovieRepository {

    override suspend fun search(page: Int, query: String): List<Movie> {
        return mapper.toDomainList(movieService.search(query, page).movies)
    }

    override suspend fun get(id: Int): Movie {
        return mapper.mapToDomainModel(movieService.get(id))
    }
}