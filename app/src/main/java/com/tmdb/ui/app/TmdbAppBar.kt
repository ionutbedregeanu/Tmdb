package com.tmdb.ui.app

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tmdb.R
import com.tmdb.ui.theme.tmdbColors

@Composable
fun TmdbAppBar(appBarTitle: String = stringResource(id = R.string.app_name)) {
    TopAppBar(
        title = { Text(text = appBarTitle, color = tmdbColors.textColor) },
        backgroundColor = tmdbColors.appBarBackground
    )
}
