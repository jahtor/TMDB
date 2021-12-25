package ru.kinesis.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.kinesis.tmdb.presentation.screens.*
import ru.kinesis.tmdb.ui.theme.TMDBTheme
import ru.kinesis.tmdb.util.ConnectionLiveData

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectionLiveData = ConnectionLiveData(this)

        setContent {
            TMDBTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val isNetworkAvailable = connectionLiveData.observeAsState(false).value

                    Navigation(isNetworkAvailable = isNetworkAvailable)
                }
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun Navigation(isNetworkAvailable: Boolean){
    //инит навигации
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search_screen") {
        //задаем экраны навигации
        composable(route = "splash_screen") {
            SplashScreen(navController)
        }
        composable(route = "search_screen") {
            SearchScreen(navController, isNetworkAvailable)
        }
        composable(
            route = "movie_info/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.IntType }
            )
        ){
            val id = remember { it.arguments?.getInt("id") }
            MovieInfo(navController, id!!)
        }
    }
}
