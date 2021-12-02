package ru.kinesis.tmdb.presentation.screens

/*
@Composable
fun SearchResultScreen(navController: NavController, search: String?) {
    val vm = MovieViewModel()
    vm.getMovieSearchList(search!!)
    Scaffold(
        topBar = {
            ru.kinesis.tmdb.presentation.AppBars.TopBar(title = search,
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
*/
