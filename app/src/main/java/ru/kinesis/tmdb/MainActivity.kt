package ru.kinesis.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kinesis.tmdb.presentation.Screen
import ru.kinesis.tmdb.presentation.movie_list.MovieListViewModel
import ru.kinesis.tmdb.presentation.screens.*
import ru.kinesis.tmdb.ui.theme.TMDBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val viewModel: MovieListViewModel by viewModels()
                    println("MovieMainActivity: ${viewModel}")


                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(){
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
/*
        composable(
            route = Screen.SearchResultScreen.route + "/{search}",
            arguments = listOf(
                navArgument("search") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            SearchResultScreen(
                navController = navController,
                search = entry.arguments?.getString("search")
            )
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
*/
    }
}
