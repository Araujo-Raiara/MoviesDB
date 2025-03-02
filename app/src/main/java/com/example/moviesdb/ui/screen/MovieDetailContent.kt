package com.example.moviesdb.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesdb.ui.common.components.TopBarTitle
import com.example.moviesdb.ui.theme.MoviesDBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsContent(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(label = "Detail")
                },
                navigationIcon = { Icons.AutoMirrored.Filled.ArrowBack })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) { }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoviesDBTheme {
        MoviesDBScreen()
    }
}

