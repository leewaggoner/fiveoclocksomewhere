package com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.models

data class DisplayCocktailState(
    val isLoading: Boolean = false,
    val cocktailName: String = "",
    val imageUrl: String = "",
    val cocktailErrorId: Int? = null,
    val cocktailError: String? = null,
    val cocktailIngredients: List<String> = listOf(),
    val cocktailMeasures: List<String> = listOf(),
    val cocktailInstructions: String = "",
)