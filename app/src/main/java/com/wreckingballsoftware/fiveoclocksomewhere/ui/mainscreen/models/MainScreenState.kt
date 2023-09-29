package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models

data class MainScreenState(
    val toast: String = "",
    val placeName: String = "",
    val errorMessage: String? = null,
    val cocktailName: String = "",
    val cocktailError: String? = null,
    val imageUrl: String = "",
    val isLoading: Boolean = false,
)