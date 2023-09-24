package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    handle: SavedStateHandle,
    cocktailsRepo: CocktailsRepo,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MainScreenState())
    }

    init {
        //calculate where it's five o'clock
        viewModelScope.launch(Dispatchers.Main) {
            val place = cocktailsRepo.getPlaceWhereIts5OClock()
        }
    }
}