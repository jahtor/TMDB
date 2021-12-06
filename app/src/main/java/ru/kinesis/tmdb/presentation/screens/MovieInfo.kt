package ru.kinesis.tmdb.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import ru.kinesis.tmdb.presentation.movie_list.MovieListViewModel

@Composable
fun MovieInfo(id: Int) {
    val viewModel: MovieListViewModel = viewModel()

    Text(text = "MovieId: $id")
//    viewModel.getMovieInfo(id)
/*
    Scaffold(
        topBar = {
            ru.kinesis.tmdb.presentation.AppBars.TopBar(title = "title",
            openSearch = {},
            openFilters = {}
        ) }
    ) {
*/
/*
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(viewModel.idList) { id ->
                Row() {
                    Image(
                        painter = rememberImagePainter("https://image.tmdb.org/t/p/w154" + id.poster_path),
                        contentDescription = "Poster preview",
                        modifier = Modifier.size(128.dp)
                    )
                    Column() {
                        Text(
                            text = id.original_title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = id.tagline)
                        Text(text = "Released: " + id.release_date)
                        if (id.revenue != 0) {
                            Text(text = "Revenue: $" + id.revenue)
                        } else Text(text = "Revenue: no data")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = id.overview)
            }
        }
*/
//    }
}
