package com.example.moviesdb.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

/*
A ser implementado futuramente
 */

private const val LANGUAGE_KEY = "LANGUAGE_KEY"
private const val DEFAULT_LANGUAGE = "pt-br"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

interface LocalDataSource {
    suspend fun getLanguage() : String
}

class PrefsSource(val context: Context): LocalDataSource {

    override suspend fun getLanguage(): String {
        val language = stringPreferencesKey(LANGUAGE_KEY)
        return context.dataStore.data.first()[language] ?: DEFAULT_LANGUAGE
    }

}
