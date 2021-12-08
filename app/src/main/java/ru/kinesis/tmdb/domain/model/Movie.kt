package ru.kinesis.tmdb.domain.model

import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

//@Parcelize
data class Movie(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
//    val belongs_to_collection: Any,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
//    val production_companies: List<ProductionCompany>,
//    val production_countries: List<ProductionCountry>,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
//    val spoken_languages: List<SpokenLanguage>,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)