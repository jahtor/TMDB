package com.example.tmdb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.remote.MovieApi
import com.example.tmdb.remote.data.MovieInfo
import com.example.tmdb.remote.data.MovieList
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

class MovieViewModel : ViewModel() {
    var movieList: List<MovieList> by mutableStateOf(listOf())
    var idList: List<MovieInfo> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    var loading = mutableStateOf(false)
/*
    //start page
    val page = mutableStateOf(1)
    var movieListPosition = 0
    private fun incPage(){
        page.value = page.value++
    }
    fun onChangeScrollPosition(position: Int){
        movieListPosition = position
    }
    private fun appendMovieList(movie: List<Result>){
        val current = ArrayList(this.movie.value)

    }

    fun nextPage(){
        viewModelScope.launch {
            loading.
        }
    }

*/

    fun getMovieList(search: String) {
        viewModelScope.launch {
//            loading.value = true
//            println("loading.value = true")
            val apiService = MovieApi.getInstance()
            try {
            loading.value = true
                movieList = listOf(apiService.getMovieList(search))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("getMovieList error: $errorMessage")
            }
            loading.value = false
//            println("loading.value = false")
        }
    }

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            val apiService = MovieApi.getInstance()
            try {
                idList = listOf(apiService.getMovieInfo(id))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("getMovieInfo error: $errorMessage")
            }
        }
    }
}
