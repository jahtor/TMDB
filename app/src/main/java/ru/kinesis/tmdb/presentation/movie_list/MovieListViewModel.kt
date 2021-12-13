package ru.kinesis.tmdb.presentation.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.repository.MovieRepository
import ru.kinesis.tmdb.util.Constants.PAGE_SIZE
import javax.inject.Inject

//const val PAGE_SIZE = 20

//ViewModel для списка фильмов (из поиска)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
    ): ViewModel(){

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())

    val query = mutableStateOf("matrix")

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)
    var movieListScrollPosition = 0

    init{
        newSearch()
    }

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

    fun nextPage(){
        viewModelScope.launch {
            //предотвращаем излишнюю загрузку данных при быстром скролле
            if((movieListScrollPosition +1) >=(page.value * PAGE_SIZE)){
                loading.value = true
//                incrementPage()
                page.value++
//                println("NextPage triggereg: ${page.value}")
//                delay(3000L) //API fakedelay
                if(page.value > 1){
                    val result = repository.search(
                        page = page.value,
                        query = query.value
                    )
//                    println("Page ${page.value}: ${result}")
                    appendMovies(result)
                }
                loading.value = false
            }
        }
    }

    //добавляем новые результаты поиска
    private fun appendMovies(movies: List<Movie>){
        val current = ArrayList(this.movies.value)
        current.addAll(movies)
        this.movies.value = current
    }

    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangeScrollPosition(position: Int){
        movieListScrollPosition = position
    }

    //функция очищает список поиска
    private fun resetSearchState(){
        movies.value = listOf()
        page.value = 1
        onChangeScrollPosition(0)
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}