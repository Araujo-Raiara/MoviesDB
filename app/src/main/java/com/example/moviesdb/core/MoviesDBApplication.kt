package com.example.moviesdb.core

import android.app.Application
import com.example.moviesdb.di.moviesDBModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesDBApplication)
            modules(moviesDBModules)
        }
    }
}
