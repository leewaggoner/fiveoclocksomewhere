package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models

data class MainScreenState(
    val toast: String = "",
    val placeName: String = "",
    val placeErrorId: Int? = null,
    val cocktailName: String = "",
    val cocktailErrorId: Int? = null,
    val cocktailError: String? = null,
    val imageUrl: String = "",
    val isLoading: Boolean = false,
)