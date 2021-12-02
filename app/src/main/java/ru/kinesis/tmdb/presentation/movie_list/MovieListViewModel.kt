package ru.kinesis.tmdb.presentation.movie_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kinesis.tmdb.repository.MovieRepository
import javax.inject.Inject

//ViewModel для списка фильмов (из поиска)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val randomString: String,
//    private val repository: MovieRepository
): ViewModel(){

    init{
        println("ViewModel: ${randomString}")
//        println("ViewModel: ${repository}")
    }
}