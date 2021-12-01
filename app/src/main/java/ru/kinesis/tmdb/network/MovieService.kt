package ru.kinesis.tmdb.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kinesis.tmdb.network.model.MovieDto
import ru.kinesis.tmdb.network.responses.MovieSearchResponse

private const val apiKey = "1d01b7b13ae2499acb0254eb582bf5d7"

interface MovieService {

    //делаем запрос на поиск и отправляем результат в MovieSearchResponse
    @GET("search/movie?api_key=$apiKey")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieSearchResponse

    //делаем запрос на информацию о фильме по id и результат в MovieNetworkEntity
    @GET("movie/{id}?api_key=$apiKey")
    suspend fun get(
        @Path("id") id: Int
    ): MovieDto
}