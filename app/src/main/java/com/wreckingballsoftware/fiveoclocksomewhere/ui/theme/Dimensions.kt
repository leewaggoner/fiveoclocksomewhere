package com.wreckingballsoftware.fiveoclocksomewhere.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val ButtonWidth: Dp = 160.dp,
    val IntroSpace: Dp = 8.dp,
    val IntroSpaceBig: Dp = 32.dp,
    val MainImageSize: Dp = 160.dp,
    val MainSpaceBig: Dp = 32.dp,
    val MainSmallSpace: Dp = 8.dp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current
