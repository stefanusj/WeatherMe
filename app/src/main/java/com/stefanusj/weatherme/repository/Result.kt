package com.stefanusj.weatherme.repository

sealed class Result<T> {
    class Loading<T> : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val t: Throwable) : Result<T>()
}