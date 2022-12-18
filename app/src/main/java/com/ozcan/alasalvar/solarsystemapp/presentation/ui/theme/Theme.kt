package com.ozcan.alasalvar.solarsystemapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = blueBGNight,
    primaryVariant = Purple700,
    secondary = cardNight,
    background = blueBGNight,
    surface = card,
    onSurface = textGreyNight
)

private val LightColorPalette = lightColors(
    primary = blueBG,
    primaryVariant = Purple700,
    secondary = card,
    background = blueBG,
    surface = blueText,
    onSurface = textGrey

)

@Composable
fun SolarSystemAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}