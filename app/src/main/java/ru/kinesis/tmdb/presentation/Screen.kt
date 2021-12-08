package ru.kinesis.tmdb.presentation

import ru.kinesis.tmdb.domain.model.Movie

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object SearchScreen : Screen("search_screen")
    object MovieInfo : Screen("movie_info")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
