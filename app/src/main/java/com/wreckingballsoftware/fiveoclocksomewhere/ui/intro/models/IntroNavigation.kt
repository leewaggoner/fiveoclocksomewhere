package com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.models

sealed class IntroNavigation {
    data object MainActivity : IntroNavigation()
}
