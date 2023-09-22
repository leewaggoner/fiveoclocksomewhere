package com.wreckingballsoftware.fiveoclocksomewhere.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.DisplayCocktail
import com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.IntroScreen
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.MainScreen

@Composable
fun FiveHost() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    val startDestination = Destinations.IntroScreen

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.IntroScreen) {
            IntroScreen(actions = actions)
        }

        composable(Destinations.MainScreen) {
            MainScreen(actions = actions)
        }

        composable(Destinations.DisplayCocktail) { backStackEntry ->
            val cocktailId = backStackEntry.arguments?.getInt("cocktailId")
            cocktailId?.let { id ->
                DisplayCocktail(
                    cocktailId = id,
                )
            }
        }
    }
}
