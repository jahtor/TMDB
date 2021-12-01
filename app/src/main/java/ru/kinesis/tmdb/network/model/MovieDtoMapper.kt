package ru.kinesis.tmdb.network.model

import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.domain.util.DomainMapper

//мапим данные между моделями
class MovieDtoMapper : DomainMapper<MovieDto, Movie> {

    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            adult = model.adult,
            backdrop_path = model.backdrop_path,
            budget = model.budget,
            homepage = model.homepage,
            id = model.id,
            imdb_id = model.imdb_id,
            original_language = model.original_language,
            original_title = model.original_title,
            overview = model.overview,
            popularity = model.popularity,
            poster_path = model.poster_path,
            release_date = model.release_date,
            revenue = model.revenue,
            runtime = model.runtime,
            status = model.status,
            tagline = model.tagline,
            title = model.original_title,
            video = model.video,
            vote_average = model.vote_average,
            vote_count = model.vote_count
        )
    }

    //не будет использоваться, т.к. мы только получаем данные из сети
    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        return MovieDto(
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            budget = domainModel.budget,
            homepage = domainModel.homepage,
            id = domainModel.id,
            imdb_id = domainModel.imdb_id,
            original_language = domainModel.original_language,
            original_title = domainModel.original_title,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            poster_path = domainModel.poster_path,
            release_date = domainModel.release_date,
            revenue = domainModel.revenue,
            runtime = domainModel.runtime,
            status = domainModel.status,
            tagline = domainModel.tagline,
            title = domainModel.original_title,
            video = domainModel.video,
            vote_average = domainModel.vote_average,
            vote_count = domainModel.vote_count
        )
    }

    //функция мапит каждый элемент списка из сетевой модели в доменную
    fun toDomainList(initial: List<MovieDto>): List<Movie>{
        return initial.map { mapToDomainModel(it) }
    }

    //функция мапит все элементы обратно в сетевую модель из доменной
    fun fromDomainList(initial: List<Movie>): List<MovieDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}