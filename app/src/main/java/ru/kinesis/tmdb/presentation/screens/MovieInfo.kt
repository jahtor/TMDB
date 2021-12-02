package ru.kinesis.tmdb.presentation.screens

/*
@Composable
fun MovieInfo(id: Int) {
    val vm = MovieViewModel()
    vm.getMovieInfo(id)
    Scaffold(
        topBar = {
            ru.kinesis.tmdb.presentation.AppBars.TopBar(title = "title",
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
*/
