package ru.kinesis.tmdb

import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.domain.model.MovieSearchList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val apiKey = "1d01b7b13ae2499acb0254eb582bf5d7"
private const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieApi {
    @GET("search/movie?api_key=$apiKey")
    suspend fun getMovieSearchList(
        @Query("query") query: String,
//        @Query("page") page: Int
    ): MovieSearchList

    @GET("movie/{id}?api_key=$apiKey")
    suspend fun getMovieInfo(
        @Path("id") name: Int
    ): Movie

    companion object {
        fun getInstance() : MovieApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MovieApi::class.java)
        }
    }
}