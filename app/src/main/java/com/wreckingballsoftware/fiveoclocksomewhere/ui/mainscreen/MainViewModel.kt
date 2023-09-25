package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CountriesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.UICocktail
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenState
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val handle: SavedStateHandle,
    private val countriesRepo: CountriesRepo,
    private val cocktailsRepo: CocktailsRepo,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MainScreenState())
    }
    private var currentCocktail: UICocktail? = null

    init {
        //calculate where it's five o'clock
        viewModelScope.launch(Dispatchers.Main) {
            state = when (val place = countriesRepo.getPlaceWhereIts5OClock()) {
                is Response.Success -> state.copy(placeName = place.data ?: "")
                is Response.Error -> state.copy(placeErrorId = place.messageId)
                else -> state
            }
            state = state.copy(isLoading = true)
            state = updateCocktail()
        }
    }

    fun chooseNewToast(toasts: Array<String>) {
        state = state.copy(toast = toasts[(0 .. toasts.size).rand()])
    }

    fun getRecipe() {

    }

    fun somethingElse() {

    }

    fun onDismissAlert() {
        state = state.copy(cocktailErrorId = null, cocktailError = null)
    }

    private suspend fun updateCocktail(): MainScreenState {
        return when (val cocktail = cocktailsRepo.getCocktailFromWhereIts5OClock()) {
            is Response.Success -> {
                val drink = cocktail.data
                if (drink != null) {
                    currentCocktail = drink
                    state.copy(
                        isLoading = false,
                        cocktailName = drink.displayName ?: "",
                        imageUrl = drink.imageUrl ?: ""
                    )
                } else {
                    currentCocktail = null
                    state.copy(
                        isLoading = false,
                        cocktailErrorId = R.string.unknown_network_error
                    )
                }
            }
            is Response.Error -> {
                currentCocktail = null
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