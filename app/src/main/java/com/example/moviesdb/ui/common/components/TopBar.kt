package com.example.moviesdb.ui.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesdb.ui.theme.MoviesDBTheme

@Composable
fun TopBarTitle(
    modifier: Modifier = Modifier,
    label: String,
) {
    Text(
        modifier = modifier,
        text = label,
        color = Color.White
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        navigationIcon = { Icons.AutoMirrored.Filled.ArrowBack })
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoviesDBTheme {
        TopBar(title = "Title")
    }
}