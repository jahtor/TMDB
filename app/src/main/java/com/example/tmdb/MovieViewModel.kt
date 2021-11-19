package com.example.tmdb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    var mList: List<MovieList> by mutableStateOf(listOf())
    var idList: List<MovieInfo> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getMovieList(s: String) {
        viewModelScope.launch {
            val apiService = MovieApi.getInstance()
            try {
                mList = listOf(apiService.getMovieList(s))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("getMovieList error: $errorMessage")
            }
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
