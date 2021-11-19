package com.example.tmdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?api_key=1d01b7b13ae2499acb0254eb582bf5d7")
    suspend fun getMovieList(
        @Query("query") query:String,
//        @Path("name") name:String
    ): MovieList

    @GET("movie/{id}?api_key=1d01b7b13ae2499acb0254eb582bf5d7")
    suspend fun getMovieInfo(
        @Path("id") name:String
    ): MovieInfo

    companion object {
        fun getInstance() : MovieApi {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MovieApi::class.java)
        }
    }
}