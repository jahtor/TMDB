package ru.kinesis.tmdb.presentation.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import ru.kinesis.tmdb.presentation.movie_info.MovieInfoViewModel
import ru.kinesis.tmdb.util.Constants.DEFAULT_MOVIE_IMAGE
import ru.kinesis.tmdb.util.LoadImage

@Composable
fun MovieInfo(navController: NavController, id: Int, viewModel: MovieInfoViewModel = hiltViewModel()) {

    val movie = viewModel.movie.value
//    Log.d("DEBUG MovieInfo", movie.toString())
    viewModel.onMovieSelect(id)

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .background(MaterialTheme.colors.primary)
    ) {
        movie.poster_path?.let { url ->

            Image(
                painter = rememberImagePainter(
                    "https://image.tmdb.org/t/p/w500" + url,
                    builder = { size(OriginalSize) }
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

//            val poster = LoadImage(
//                url = "https://image.tmdb.org/t/p/w500" + url,
//                defaultImage = DEFAULT_MOVIE_IMAGE
//            ).value
//            poster?.let { img ->
//                Image(
//                    bitmap = img.asImageBitmap(),
//                    contentDescription = "poster",
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.FillWidth
//                )
//            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            movie.title?.let { title ->
                Text( // название (год)
                    text = "${movie.title}" + " (${movie.release_date.toString().take(4)})",
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(8.dp)
                        .wrapContentWidth(CenterHorizontally),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
            }

            Row( // жанры
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
            ) {
                movie.genres?.forEach { genre ->
                    Text(
                        text = "${genre.name} ",
                        color = Color.White,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
//                    .wrapContentWidth(CenterHorizontally)
            ) {
                Text( // рейтинг пользователей
                    text = "User Score: ${movie.vote_average?.times(10)}%",
                    color = Color.White,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                )

                Text( //хронометраж
                    text = "${movie.runtime?.div(60)}h ${movie.runtime?.rem(60)}m",
                    color = Color.White,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = "Overview:",
                color = Color.White,
                style = MaterialTheme.typography.h6
            )

            movie.overview?.let { overview ->
                Text( // обзор
                    text = "${movie.overview}",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

