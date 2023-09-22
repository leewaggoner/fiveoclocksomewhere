package com.wreckingballsoftware.fiveoclocksomewhere.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.models.IntroNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class IntroViewModel : ViewModel() {
    val navigation = MutableSharedFlow<IntroNavigation>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    fun onButtonClick() {
        viewModelScope.launch(Dispatchers.Main) {
            navigation.emit(IntroNavigation.MainActivity)
        }
    }
}