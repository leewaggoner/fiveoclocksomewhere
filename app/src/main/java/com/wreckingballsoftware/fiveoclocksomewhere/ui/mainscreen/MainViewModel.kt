package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBPlaces
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.PlacesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.UICocktail
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenNavigation
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenState
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    handle: SavedStateHandle,
    private val placesRepo: PlacesRepo,
    private val cocktailsRepo: CocktailsRepo,
) : ViewModel() {
    val navigation = MutableSharedFlow<MainScreenNavigation>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MainScreenState())
    }
    private var toasts: List<String> = listOf()
    private var currentCocktailId: Long = -1

    init {
        fetchCocktailData { place ->
            fetchCocktail(place)
        }
    }

    fun createToastList(resourceToasts: Array<String>) {
        toasts = resourceToasts.toList()
        newToast()
    }

    private fun newToast() {
        state = state.copy(toast = toasts[(0 .. toasts.size).rand()])
    }

    fun getRecipe() {
        if (currentCocktailId != -1L) {
            viewModelScope.launch(Dispatchers.Main) {
                navigation.emit(MainScreenNavigation.DisplayCocktail(currentCocktailId))
            }
        }
    }

    fun somethingElse() {
        //fetch a random cocktail
        fetchCocktailData {
            newToast()
            fetchNewCocktail()
        }
    }

    fun onDismissAlert() {
        state = state.copy(errorMessage = null)
    }

    private fun fetchCocktailData(cocktailCall: (suspend (DBPlaces?) -> MainScreenState)) {
        viewModelScope.launch(Dispatchers.Main) {
            state = state.copy(isLoading = true)
            val placeResponse = placesRepo.getPlaceWhereIts5OClock()
            state = when (placeResponse) {
                is Response.Success -> {
                    state = state.copy(placeName = placeResponse.data?.name ?: "")
                    cocktailCall(placeResponse.data)
                }
                is Response.Error -> state.copy(errorMessage = placeResponse.errorMessage)
                else -> state
            }
        }
    }

    private suspend fun fetchCocktail(place: DBPlaces?): MainScreenState {
        val cocktail = cocktailsRepo.getCocktailFromWhereIts5OClock(
            place ?: DBPlaces(id = 14, name = "", timeZoneId = 0) //default to Pepeete
        )
        return handleCocktailResponse(cocktail)
    }

    private suspend fun fetchNewCocktail(): MainScreenState {
        val cocktail = cocktailsRepo.getRandomCocktail()
        return handleCocktailResponse(cocktail)
    }

    private fun handleCocktailResponse(cocktail: Response<UICocktail>): MainScreenState {
        return when (cocktail) {
            is Response.Success -> {
                val drink: UICocktail? = cocktail.data
                if (drink != null) {
                    currentCocktailId = drink.id ?: -1L
                    state.copy(
                        isLoading = false,
                        cocktailName = drink.displayName ?: "",
                        imageUrl = drink.imageUrl ?: ""
                    )
                } else {
                    state.copy(
                        isLoading = false,
                        cocktailError = "Unknown server error."
                    )
                }
            }
            is Response.Error -> {
                state.copy(
                    isLoading = false,
                    cocktailError = cocktail.errorMessage,
                )
            }
            else -> state
        }
    }
}