package ru.kinesis.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.kinesis.tmdb.presentation.Screen
import ru.kinesis.tmdb.presentation.movie_list.MovieListViewModel
import ru.kinesis.tmdb.presentation.screens.*
import ru.kinesis.tmdb.ui.theme.TMDBTheme

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                Surface(color = MaterialTheme.colors.background) {

//                    SearchScreen()
//                    MovieInfo(585245)
                    Navigation()
                }
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun Navigation(){
    //инит навигации
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SearchScreen.route) {
        //задаем экраны навигации
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.SearchScreen.route) {
            val viewModel = hiltViewModel<MovieListViewModel>()
            SearchScreen(navController)
        }
        composable(route = Screen.MovieInfo.route){
            val viewModel = hiltViewModel<MovieListViewModel>()
            MovieInfo()
        }
/*
        composable(
            route = Screen.MovieInfo.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<MovieListViewModel>()
            MovieInfo(id = entry.arguments?.getString("id")!!)
        }
*/
    }
}
