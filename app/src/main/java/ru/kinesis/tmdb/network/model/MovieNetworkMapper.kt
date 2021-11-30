package ru.kinesis.tmdb.network.model

import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.domain.util.EntityMapper

//мапим данные между моделями
class MovieNetworkMapper : EntityMapper<MovieNetworkEntity, Movie> {

    override fun mapFromEntity(entity: MovieNetworkEntity): Movie {
        return Movie(
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            budget = entity.budget,
            homepage = entity.homepage,
            id = entity.id,
            imdb_id = entity.imdb_id,
            original_language = entity.original_language,
            original_title = entity.original_title,
            overview = entity.overview,
            popularity = entity.popularity,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            revenue = entity.revenue,
            runtime = entity.runtime,
            status = entity.status,
            tagline = entity.tagline,
            title = entity.original_title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count
        )
    }

    //не будет использоваться, т.к. мы только получаем данные из сети
    override fun mapToEntity(domainModel: Movie): MovieNetworkEntity {
        return MovieNetworkEntity(
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
    fun fromEntityList(initial: List<MovieNetworkEntity>): List<Movie>{
        return initial.map { mapFromEntity(it) }
    }

    //функция мапит все элементы обратно в сетевую модель из доменной
    fun toEntityList(initial: List<Movie>): List<MovieNetworkEntity>{
        return initial.map { mapToEntity(it) }
    }
}