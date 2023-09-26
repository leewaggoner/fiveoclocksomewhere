package com.wreckingballsoftware.fiveoclocksomewhere

import androidx.navigation.NavController

object Destinations {
    const val IntroScreen = "IntroScreen"
    const val MainScreen = "MainScreen"
    const val DisplayCocktail = "DisplayCocktail/{cocktailId}"
}

class Actions(navController: NavController) {
    val navigateToMainScreen: () -> Unit = {
        navController.navigate(
            Destinations.MainScreen
        )
    }
    val navigateToDisplayCocktail: (Long) -> Unit = { cocktailId ->
        navController.navigate(
            Destinations.DisplayCocktail.replace(
                oldValue = "{cocktailId}",
                newValue = cocktailId.toString()
            )
        )
    }
}
