package com.example.tmdb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.remote.MovieApi
import com.example.tmdb.remote.data.MovieInfo
import com.example.tmdb.remote.data.MovieSearchList
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

class MovieViewModel : ViewModel() {
    //переменная в которую сохраняем список поиска
    var movieSearchSearchList: List<MovieSearchList> by mutableStateOf(listOf())
    //переменная в которую сохраняем список информации о фильме
    var idList: List<MovieInfo> by mutableStateOf(listOf())
    //переменная с инфо об ошибках
    var errorMessage: String by mutableStateOf("")

    //функция запроса поиска из API
    fun getMovieSearchList(search: String) {
        viewModelScope.launch {
            val apiService = MovieApi.getInstance()
            try {
                movieSearchSearchList = listOf(apiService.getMovieSearchList(search))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("getMovieSearchList error: $errorMessage")
            }
        }
    }

    //функция запроса информации о фильме по id из API
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
