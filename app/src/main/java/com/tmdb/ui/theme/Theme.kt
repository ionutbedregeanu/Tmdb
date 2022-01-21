package com.tmdb.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val localColors = staticCompositionLocalOf { lightColorPalette() }

@Composable
fun TmdbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette()
    } else {
        lightColorPalette()
    }
    CompositionLocalProvider(localColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val tmdbColors: TmdbColors
    @Composable
    @ReadOnlyComposable
    get() = localColors.current

private fun darkColorPalette():TmdbColors = TmdbColors(
    material = darkColorsMaterial(),
    appBarBackground = DarkBlue,
    textColor = Color.White,
    cardColor = DarkBlue,
    ratingBackground = DarkBlueA5
)

private fun lightColorPalette():TmdbColors = TmdbColors(
    material = lightColorsMaterial(),
    appBarBackground = LightBlue,
    textColor = Color.Black,
    cardColor = LightBlue,
    ratingBackground = LightBlueA2
)

/**
 * Material baseline colors can be overridden here.
 */
private fun darkColorsMaterial(): Colors = darkColors(
    surface = Color.White,
    onSurface = Color.White,
    primary = DarkBlue,
    secondary = LightBlue,
)

private fun lightColorsMaterial(): Colors = lightColors(
    surface = Color.White,
    onSurface = Color.White,
    primary = DarkBlue,
    secondary = LightBlue,
)
