package com.example.moviesdb.di

import com.example.moviesdb.core.Constants.Network.AUTHENTICATION_KEY
import com.example.moviesdb.core.Constants.Network.AUTHENTICATION_VALUE
import com.example.moviesdb.core.Constants.Network.LANGUAGE_KEY
import com.example.moviesdb.core.Constants.Network.LANGUAGE_VALUE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MovieApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalUrl = chain.request().url
        val url = originalUrl.newBuilder().addQueryParameter(LANGUAGE_KEY, LANGUAGE_VALUE).build()

        val request: Request =
            chain.request().newBuilder().addHeader(AUTHENTICATION_KEY, AUTHENTICATION_VALUE)
                .url(url).build()
        return chain.proceed(request)
    }
}
