package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainScreenState(
    val toast: String = "",
    val placeName: String = "",
    val errorMessage: String? = null,
    val cocktailName: String = "",
    val cocktailError: String? = null,
    val imageUrl: String = "",
    val isLoading: Boolean = false,
) : Parcelable