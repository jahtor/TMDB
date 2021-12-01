package ru.kinesis.tmdb.presentation.movie_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//ViewModel для списка фильмов (из поиска)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val randomString: String
): ViewModel(){

    init{
        println("ViewModel: ${randomString}")
    }
}