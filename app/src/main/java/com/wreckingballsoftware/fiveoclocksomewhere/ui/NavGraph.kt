package com.wreckingballsoftware.fiveoclocksomewhere.ui

import androidx.navigation.NavController

object Destinations {
    const val MainScreen = "MainScreen"
    const val DisplayCocktail = "DisplayCocktail/{cocktailId}"
}

class Actions(navController: NavController) {
    val navigateToDisplay: (Int) -> Unit = { cocktailId ->
        navController.navigate(
            Destinations.DisplayCocktail.replace(
                oldValue = "{cocktailId}",
                newValue = cocktailId.toString()
            )
        )
    }
}
