package com.example.tmdb

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

object AppBars {
    @Composable
    fun TopBar(title: String, openSearch: () -> Unit, openFilters: () -> Unit) {
        TopAppBar(
            title = { Text(text = title, color = Color.White) },
            backgroundColor = Color(R.color.TMDB_primary),
//            actions = {
//                IconButton(onClick = openSearch) {
//                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
//                }
//                IconButton(onClick = openFilters) {
//                    Icon(imageVector = Icons.Filled.FilterList, contentDescription = "Filter")
//                }
//            }
        )
    }

//    @Composable
//    fun BottomBar() {
//        val items = listOf(
//            Screen.SearchScreen,
//            Screen.MovieInfo
//        )
//        BottomNavigation(
//            backgroundColor = Color(R.color.TMDB_primary),
//            contentColor = Color.White
//        ) {
//            items.forEach { item ->
//                BottomNavigationItem(
//                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
//                    label = { Text(text = item.route) },
//                    selectedContentColor = Color.White,
//                    unselectedContentColor = Color.White.copy(0.4f),
//                    alwaysShowLabel = true,
//                    selected = false,
//                    onClick = {
//                        /* Add code later */
//                    }
//                )
//            }
//        }
//    }

}
