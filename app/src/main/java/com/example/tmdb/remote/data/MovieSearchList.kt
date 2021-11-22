package com.example.tmdb.remote.data

data class MovieSearchList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)