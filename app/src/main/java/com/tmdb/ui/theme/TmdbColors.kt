package com.tmdb.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

data class TmdbColors(
    val material: Colors,
    val appBarBackground: Color,
    val textColor: Color,
    val cardColor: Color,
    val ratingBorder: Color = LightGreen,
    val ratingBackground: Color
)
