package com.example.moviesdb.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.moviesdb.ui.navigation.MovieDBNavHost
import com.example.moviesdb.ui.theme.MoviesDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesDBTheme {
                MovieDBNavHost(navController = rememberNavController())
            }
        }
    }
}