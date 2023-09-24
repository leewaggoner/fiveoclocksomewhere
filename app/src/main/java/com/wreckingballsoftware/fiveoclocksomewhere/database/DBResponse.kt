package com.wreckingballsoftware.fiveoclocksomewhere.database

sealed class DBResponse {
    data class Success(val data: String) : DBResponse()
    data class Error(val errorMessageId: Int) : DBResponse()
}