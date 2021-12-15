package ru.kinesis.tmdb.network.responses

import com.google.gson.annotations.SerializedName
import ru.kinesis.tmdb.domain.model.Result
import ru.kinesis.tmdb.network.model.MovieDto

data class MovieSearchResponse(

    @SerializedName("total_results")
    var count: Int,

    @SerializedName("results")
//    var movies: List<Result>
    var movies: List<MovieDto>
)