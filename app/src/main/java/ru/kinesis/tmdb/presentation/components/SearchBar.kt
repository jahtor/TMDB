package ru.kinesis.tmdb.presentation.movie_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.kinesis.tmdb.R

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    newSearch: () -> Unit,
    keyboardController: SoftwareKeyboardController
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_tmdb_blue_long),
                contentDescription = "logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
            )
            TextField(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                value = query,
                onValueChange = { onQueryChanged(it) },
                label = { Text(text = "Search") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    textColor = MaterialTheme.colors.onSurface
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    newSearch()
                    keyboardController.hide()
                }),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "",
                    )
                },
                singleLine = true
            )
        }
    }
}