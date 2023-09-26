package com.wreckingballsoftware.fiveoclocksomewhere

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        composable(
            Destinations.DisplayCocktail,
            arguments = listOf(navArgument("cocktailId") { type = NavType.LongType })
            ) { backStackEntry ->
            val cocktailId = backStackEntry.arguments?.getLong("cocktailId")
            cocktailId?.let { id ->
                DisplayCocktail(
                    cocktailId = id,
                )
            }
        }
    }
}
