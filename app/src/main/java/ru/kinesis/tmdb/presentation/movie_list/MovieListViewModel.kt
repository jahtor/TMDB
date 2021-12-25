package ru.kinesis.tmdb.presentation.movie_list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.presentation.MovieApplication
import ru.kinesis.tmdb.repository.MovieRepository
import ru.kinesis.tmdb.util.Constants.PAGE_SIZE
import javax.inject.Inject

//ViewModel для списка фильмов (из поиска)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
    app: Application
//    ): ViewModel(){
    ): AndroidViewModel(app){

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
//            delay(3000L) //API fakedelay
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

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MovieApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}