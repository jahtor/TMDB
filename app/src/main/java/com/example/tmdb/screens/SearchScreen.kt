package com.example.tmdb.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.R
import com.example.tmdb.Screen

@Composable
fun SearchScreen(navController: NavController) {
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_tmdb_blue_long),
                contentDescription = "logo",
                modifier = Modifier.align(CenterHorizontally).padding(horizontal = 16.dp)
            )
/*
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
*/
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
                    } else navController.navigate(Screen.SearchResultScreen.withArgs(search))
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


/*
@Composable
fun SearchBar(    modifier: Modifier = Modifier, hint: String = "", onSearch: (String) -> Unit = {}){
    var search by remember { mutableStateOf("")}
    var isHintDisplayed by remember { mutableStateOf(hint != "")}
    Box(modifier = modifier) {
        BasicTextField(
            value = search,
            onValueChange = {
                search = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .onFocusChanged { isHintDisplayed = it != FocusState.Active }
        )
    }
}
*/
