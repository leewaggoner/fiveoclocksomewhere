package com.wreckingballsoftware.fiveoclocksomewhere.repos.models

sealed class Response<T>(
    val data: T? = null,
    val errorMessage: String? = null,
) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(
        errorMessage: String? = null,
    ) : Response<T>(errorMessage = errorMessage)
    class Loading<T>() : Response<T>()
}