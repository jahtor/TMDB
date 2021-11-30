package ru.kinesis.tmdb.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.tmdb.MovieViewModel
import com.example.tmdb.Screen

@Composable
fun SearchResultScreen(navController: NavController, search: String?) {
    val vm = MovieViewModel()
    vm.getMovieSearchList(search!!)
    Scaffold(
        topBar = {
            ru.kinesis.tmdb.AppBars.TopBar(title = search,
            openSearch = {},
            openFilters = {}
        ) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color.White)
        ) {
            items(vm.movieSearchList) { movie ->
                movie.results.forEach { t ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .clickable {
//                                println(t.id)
                                navController.navigate(Screen.MovieInfo.withArgs(t.id))
                            },
                        elevation = 10.dp,
                        border = BorderStroke(2.dp, Color.Gray),
//            backgroundColor = Color.LightGray
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(150.dp)
                                .padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = rememberImagePainter("https://image.tmdb.org/t/p/w154" + t.poster_path),
                                contentDescription = "Poster preview",
                                modifier = Modifier.size(128.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(15.dp),
//                    verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = t.title, fontWeight = FontWeight.Bold)
//                    Text(text = t.release_date)
                            }
                        }
                    }
                }
            }
        }
    }
}
