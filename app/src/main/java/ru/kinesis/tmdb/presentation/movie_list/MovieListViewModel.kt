package ru.kinesis.tmdb.presentation.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.repository.MovieRepository
import javax.inject.Inject

//ViewModel для списка фильмов (из поиска)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())

    val query = mutableStateOf("")

    val loading = mutableStateOf(false)

//    init{
//        newSearch("Movie")
//    }

    fun newSearch(){
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
//            delay(3000L)
            val result = repository.search(
                page = 1,
                query = query.value
            )
            movies.value = result
            loading.value = false
        }
    }

    //функция очищает список поиска
    private fun resetSearchState(){
        movies.value = listOf()
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}