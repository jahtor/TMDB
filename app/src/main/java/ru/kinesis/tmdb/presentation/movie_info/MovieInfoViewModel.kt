package ru.kinesis.tmdb.presentation.movie_info

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repository: MovieRepository
    ): ViewModel() {

    val loading = mutableStateOf(false)

    val movieId = mutableStateOf(666)

    val movie: MutableState<Movie> = mutableStateOf(Movie())

//    init{
//        movieGet()
//    }

    fun movieGet(){
        viewModelScope.launch {
            loading.value = true
            val result = repository.get(id = movieId.value)
            movie.value = result
            loading.value = false
        }
    }

    fun onMovieSelect(id: Int){
        this.movieId.value = id
        movieGet()
    }

}