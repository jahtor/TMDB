package com.example.tmdb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tmdb.AppBars
import com.example.tmdb.MovieViewModel

@Composable
fun MovieInfo(id: String) {
    val vm = MovieViewModel()
    vm.getMovieInfo(id)
//    val title = vm.idList.value
    println(title)
    Scaffold(
        topBar = {
            AppBars.TopBar(title = "title",
            openSearch = {},
            openFilters = {}
        ) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(vm.idList) { id ->
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
    }
}
