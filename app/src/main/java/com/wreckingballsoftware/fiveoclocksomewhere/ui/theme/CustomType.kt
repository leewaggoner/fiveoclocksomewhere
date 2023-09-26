package com.wreckingballsoftware.fiveoclocksomewhere.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


data class CustomTypeStyles(
    val fiveTitle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        textAlign = TextAlign.Center,
    ),
    val fiveSubtitle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        textAlign = TextAlign.Center,
    ),
    val fiveBody: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
    ),
    val fiveInstructions: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center,
    )
)

val FiveCustomTypography = CustomTypeStyles()

val LocalCustomTypography = staticCompositionLocalOf { CustomTypeStyles() }

val MaterialTheme.customTypography: CustomTypeStyles
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomTypography.current

