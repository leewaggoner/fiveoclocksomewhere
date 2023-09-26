package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models

sealed interface MainScreenNavigation {
    data class DisplayCocktail(val cocktailId: Long) : MainScreenNavigation
}