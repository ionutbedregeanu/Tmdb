package com.tmdb.ui.app

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tmdb.R
import com.tmdb.ui.theme.tmdbColors

@Composable
fun TmdbAppBar(
    appBarTitle: String = stringResource(id = R.string.app_name),
    navController: NavController? = null
) {
    TopAppBar(
        title = { Text(text = appBarTitle, color = tmdbColors.textColor) },
        backgroundColor = tmdbColors.appBarBackground,
        navigationIcon = if (navController?.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = tmdbColors.textColor
                    )
                }
            }
        } else {
            null
        }
    )
}
