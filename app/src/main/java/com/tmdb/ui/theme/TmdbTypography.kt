package com.tmdb.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class TmdbTypography(
    val materialTypography: Typography,
    val cardTitle: TextStyle,
    val cardReleaseDate: TextStyle
)

val tmdbTypography: TmdbTypography
    @Composable
    @ReadOnlyComposable
    get() = TmdbTypography(
        materialTypography = Typography(
            body1 = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        ),
        cardTitle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = tmdbColors.textColor
        ),
        cardReleaseDate = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = tmdbColors.textColor
        )
    )
