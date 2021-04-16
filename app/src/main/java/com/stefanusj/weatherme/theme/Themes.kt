package com.stefanusj.weatherme.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Blue200 = Color(0xFF408CFF)
private val Blue500 = Color(0xFF0066FF)

private val Red800 = Color(0xFFD00036)

private val DarkPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue200,
    onPrimary = Color.Black,
    secondary = Blue200,
    onSecondary = Color.Black,
    onSurface = Color.White,
    onBackground = Color.White,
    error = Red800,
    onError = Color.Black
)

private val LightPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue500,
    onPrimary = Color.White,
    secondary = Blue500,
    onSecondary = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black,
    error = Red800,
    onError = Color.White
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colors: Colors? = null,
    content: @Composable () -> Unit
) {
    val myColors = colors ?: if (isDarkTheme) DarkPalette else LightPalette

    MaterialTheme(
        colors = myColors,
        content = content,
        typography = AppTypography,
        shapes = AppShapes
    )
}
