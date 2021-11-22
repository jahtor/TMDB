package com.example.tmdb

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.example.tmdb.ui.theme.TMDBTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //инит навигации
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SearchScreen.route) {
                        //задаем экраны навигации
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController)
                        }
                        composable(route = Screen.SearchScreen.route) {
                            SearchScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CardScreen.route + "/{search}",
                            arguments = listOf(
                                navArgument("search") {
                                    type = NavType.StringType
//                    defaultValue = ""
                                    nullable = true
                                }
                            )
                        ) { entry ->
                            CardScreen(navController = navController, search = entry.arguments?.getString("search"))
                        }
                        composable(
                            route = Screen.MovieInfo.route + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                }
                            )
                        ) { entry ->
                            MovieInfo(id = entry.arguments?.getString("id")!!)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(Screen.SearchScreen.route)
    }

    // Image #1abc9c
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
//            .background(color = Color.Green)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_tmdb_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
//    Image(
//        painter = painterResource(id = R.drawable.ic_tmdb_logo),
//        contentDescription = "logo"
//    )
    Scaffold(
        topBar = {AppBars.TopBar(title = "Search",
            openSearch = {},
            openFilters = {}
        ) }

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
//            .background(color = Color(R.color.TMDB_primary))
        ) {
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxSize()
            ) {
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    value = search,
                    onValueChange = { search = it },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (search == "") {
                            Toast.makeText(context, "Empty search!", Toast.LENGTH_LONG).show()
                        } else navController.navigate(Screen.CardScreen.withArgs(search))
                    },
                    modifier = Modifier
                        .size(width = 150.dp, height = 50.dp)
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                        .align(Alignment.End)
                        .wrapContentWidth(),
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(R.color.TMDB_secondary)),
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        top = 5.dp,
                        end = 5.dp,
                        bottom = 5.dp
                    )
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Search")
                }
            }
        }
    }
}

@Composable
fun CardScreen(navController: NavController, search: String?) {
    val vm = MovieViewModel()
    vm.getMovieList(search!!)
    val loading = vm.loading.value
    println(loading)
//    Box(modifier = Modifier.fillMaxSize()) {
    Scaffold(
        topBar = {AppBars.TopBar(title = search,
            openSearch = {},
            openFilters = {}
        ) }
    ) {

        CircularBar(isDisplayed = loading)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color.White)
        ) {
            items(vm.movieList) { movie ->
                movie.results.forEach { t ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .clickable {
                                println(t.id)
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

@Composable
fun MovieInfo(id: String) {
    val vm = MovieViewModel()
    vm.getMovieInfo(id)
    Scaffold(
        topBar = {AppBars.TopBar(title = "title",
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

/*
@Composable
fun CircularLoad(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
*/
