package com.wreckingballsoftware.fiveoclocksomewhere.repos.models

sealed class Response<T>(
    val data: T? = null,
    val message: String? = null,
    val messageId: Int? = null,
) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(
        errorMessage: String? = null,
        errorMessageId: Int? = null,
    ) : Response<T>(message = errorMessage, messageId = errorMessageId)
    class Loading<T>() : Response<T>()
}