package ru.kinesis.tmdb.presentation.movie_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.kinesis.tmdb.presentation.components.MovieCard

//отображаем список поиска из элементов MovieCard
@Composable
fun MovieList(viewModel: MovieListViewModel = viewModel()) {

    val movies = viewModel.movies.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.White)
    ) {
        itemsIndexed(
            items = movies
        ){ index, movie ->
            MovieCard(
                movie = movie,
                onCLick = {},
//                navController = rememberNavController()
            )
        }
    }
}
