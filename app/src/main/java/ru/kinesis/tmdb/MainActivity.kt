package ru.kinesis.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tmdb.screens.*
import com.example.tmdb.ui.theme.TMDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
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
                            route = Screen.SearchResultScreen.route + "/{search}",
                            arguments = listOf(
                                navArgument("search") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                    nullable = true
                                }
                            )
                        ) { entry ->
                            SearchResultScreen(navController = navController, search = entry.arguments?.getString("search"))
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
