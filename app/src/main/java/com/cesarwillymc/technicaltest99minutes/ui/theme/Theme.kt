package com.cesarwillymc.technicaltest99minutes.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Cultured,
    secondary = ForestGreen,
    background = White,
    surface = White,
    onPrimary = Gray,
    onSecondary = White,
    onBackground = Gray,
    onSurface = Gray
)

/**
 * For now always Light Theme
 */
@Composable
fun GreenCrossTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
