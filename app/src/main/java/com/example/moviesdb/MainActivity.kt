package com.example.moviesdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesdb.ui.screen.MoviesDBScreen
import com.example.moviesdb.ui.theme.MoviesDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesDBTheme {
                MoviesDBScreen()
            }
        }
    }
}