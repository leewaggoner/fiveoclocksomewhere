package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBResponse
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CountriesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenState
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    handle: SavedStateHandle,
    countriesRepo: CountriesRepo,
    cocktailsRepo: CocktailsRepo,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MainScreenState())
    }

    init {
        //calculate where it's five o'clock
        viewModelScope.launch(Dispatchers.Main) {
            state = when (val place = countriesRepo.getPlaceWhereIts5OClock()) {
                is DBResponse.Success -> state.copy(placeName = place.data)
                is DBResponse.Error -> state.copy(placeErrorId = place.errorMessageId)
            }
            state = when (val cocktail = cocktailsRepo.getCocktailFromWhereIts5OClock()) {
                is DBResponse.Success -> state.copy(cocktailName = cocktail.data)
                is DBResponse.Error -> state.copy(cocktailErrorId = cocktail.errorMessageId)
            }
        }
    }

    fun chooseNewToast(toasts: Array<String>) {
        state = state.copy(toast = toasts[(0 .. toasts.size).rand()])
    }

    fun getRecipe() {

    }

    fun somethingElse() {

    }
}