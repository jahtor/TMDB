package ru.kinesis.tmdb.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kinesis.tmdb.domain.model.MovieSearchList
import ru.kinesis.tmdb.domain.model.Result
import ru.kinesis.tmdb.network.model.MovieDto
import ru.kinesis.tmdb.network.responses.MovieSearchResponse
import ru.kinesis.tmdb.util.Constants.API_KEY

interface MovieService {

    //делаем запрос на поиск
    @GET("search/movie?api_key=$API_KEY")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int
//    ): MovieSearchList
    ): MovieSearchResponse

    //делаем запрос на информацию о фильме по id
    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun get(
        @Path("id") id: Int
//    ): Result
    ): MovieDto
}