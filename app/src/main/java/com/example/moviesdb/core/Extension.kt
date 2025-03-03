package com.example.moviesdb.core

fun Int?.orOne() = this ?: 1

fun Int?.orZero() = this ?: 0

fun String.yearMonthDayToYear(): String {
    return try {
        split("-").firstOrNull().orEmpty()
    } catch (e: Exception) {
        String()
    }
}