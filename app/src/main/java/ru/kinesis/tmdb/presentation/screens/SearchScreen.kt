package ru.kinesis.tmdb.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.kinesis.tmdb.R
import ru.kinesis.tmdb.presentation.components.CircularProgressBar
import ru.kinesis.tmdb.presentation.components.MovieCard
import ru.kinesis.tmdb.presentation.movie_info.MovieInfoViewModel
import ru.kinesis.tmdb.presentation.movie_list.MovieListViewModel
import ru.kinesis.tmdb.repository.MovieRepository
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
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = 8.dp
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_tmdb_blue_long),
                    contentDescription = "logo",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(horizontal = 16.dp)
                )
                TextField(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    value = query,
                    onValueChange = { viewModel.onQueryChanged(it) },
                    label = { Text(text = "Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onSurface
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.newSearch()
                        keyboardController?.hide()
                    }),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "",
                        )
                    },
                    singleLine = true
                )
            }
        }
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
