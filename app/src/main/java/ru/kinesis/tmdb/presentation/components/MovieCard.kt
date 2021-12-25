package ru.kinesis.tmdb.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.kinesis.tmdb.domain.model.Movie
import ru.kinesis.tmdb.util.Constants.DEFAULT_MOVIE_IMAGE

//создаем карточку фильма в списке поиска
@Composable
fun MovieCard(
    movie: Movie,
    onCLick: () -> Unit,
){
    val height = 200.dp
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .height(height)
            .clickable(onClick = onCLick),
        elevation = 8.dp
    ){
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start
        ){
            if(movie.poster_path != null) {
                Image(
                    painter = rememberImagePainter("https://image.tmdb.org/t/p/w185" + movie.poster_path),
                    contentDescription = "Poster preview",
                    modifier = Modifier.height(height),
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.CenterStart
                )
            } else {
                Image(
                    painter = painterResource(id = DEFAULT_MOVIE_IMAGE),
                    contentDescription = "No Poster",
                    modifier = Modifier
                        .height(height),
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.CenterStart
                )
            }

            movie.title?.let { title ->
                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(text = title + " (${movie.release_date.toString().take(4)})",
                        modifier = Modifier
                            .fillMaxWidth(),
//                            .wrapContentWidth(Alignment.Start),
                        color = Color.White,
                        style = MaterialTheme.typography.h6)

/*
                    Text(text = movie.release_date.toString(),
                        modifier = Modifier
                            .fillMaxWidth(),
//                            .wrapContentWidth(Alignment.Start),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body1)
//                    Spacer(modifier = Modifier.height(8.dp))
*/

                    Text(text = movie.overview.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}