package com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.models.DisplayCocktailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayCocktailViewModel(
    handle: SavedStateHandle,
    private val cocktailsRepo: CocktailsRepo,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(DisplayCocktailState())
    }

    fun getCocktailRecipe(cocktailId: Long) {
        state = state.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            state = getCocktail(cocktailId)
        }
    }

    fun onDismissAlert() {
        state = state.copy(cocktailErrorId = null, cocktailError = null)
    }


    private suspend fun getCocktail(cocktailId: Long): DisplayCocktailState {
        return when (val cocktail = cocktailsRepo.getCocktailById(cocktailId)) {
            is Response.Success -> {
                val drink = cocktail.data
                if (drink != null) {
                    state.copy(
                        isLoading = false,
                        cocktailName = drink.displayName ?: "",
                        imageUrl = drink.imageUrl ?: "",
                        cocktailInstructions = drink.instructions ?: "",
                        cocktailIngredients = drink.ingredients ?: listOf(),
                        cocktailMeasures = drink.measures ?: listOf(),
                    )
                } else {
                    state.copy(
                        isLoading = false,
                        cocktailErrorId = R.string.unknown_network_error
                    )
                }
            }
            is Response.Error -> {
                state.copy(
                    isLoading = false,
                    cocktailErrorId = cocktail.messageId,
                    cocktailError = cocktail.message,
                )
            }
            else -> state
        }
    }
}