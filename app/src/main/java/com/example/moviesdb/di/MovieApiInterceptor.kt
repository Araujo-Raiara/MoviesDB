package com.example.moviesdb.di

import com.example.moviesdb.core.Constants.Network.AUTHENTICATION_KEY
import com.example.moviesdb.core.Constants.Network.AUTHENTICATION_VALUE
import com.example.moviesdb.core.Constants.Network.LANGUAGE_KEY
import com.example.moviesdb.core.Constants.Network.LANGUAGE_PORTUGUESE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.Locale

class MovieApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * Send the language as portuguese if it's the device's language
         * Otherwise don't send it, english is the default language for the MovieDbApi
         */
        val language = Locale.getDefault().toLanguageTag().lowercase()
        val url = if(language == LANGUAGE_PORTUGUESE) {
            val originalUrl = chain.request().url
            originalUrl.newBuilder().addQueryParameter(LANGUAGE_KEY, language).build()
        } else {
            chain.request().url
        }

        val request: Request =
            chain.request().newBuilder().addHeader(AUTHENTICATION_KEY, AUTHENTICATION_VALUE)
                .url(url).build()
        return chain.proceed(request)
    }
}
