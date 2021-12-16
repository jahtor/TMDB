package ru.kinesis.tmdb.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.kinesis.tmdb.presentation.components.CircularProgressBar
import ru.kinesis.tmdb.presentation.components.MovieCard
import ru.kinesis.tmdb.presentation.movie_list.MovieListViewModel
import ru.kinesis.tmdb.presentation.movie_list.SearchBar
import ru.kinesis.tmdb.util.Constants.PAGE_SIZE

@Composable
@ExperimentalComposeUiApi
fun SearchScreen(navController: NavController, viewModel: MovieListViewModel = hiltViewModel()) {

    val movies = viewModel.movies.value
    val query = viewModel.query.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val loading = viewModel.loading.value
    val page = viewModel.page.value

//    val movieInfoVM: MovieInfoViewModel = hiltViewModel()

    Column {
        SearchBar(
            query = query,
            onQueryChanged = viewModel::onQueryChanged,
            newSearch = viewModel::newSearch,
            keyboardController = keyboardController!!
        )
        // отображаем результаты поиска
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                //            .background(Color.White)
            ) {
                itemsIndexed(
                    items = movies
                ) { index, movie ->
                    viewModel.onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        viewModel.nextPage()
                    }
                    MovieCard(
                        movie = movie,
                        onCLick = {
//                            movieInfoVM.movieId.value = movie.id!!
//                            println("onClick id: ${movieInfoVM.movieId.value}")
                            navController.navigate("movie_info/${movie.id}")
                        },
                    )
                }
            }
            CircularProgressBar(isDisplayed = loading)
        }
    }
}
