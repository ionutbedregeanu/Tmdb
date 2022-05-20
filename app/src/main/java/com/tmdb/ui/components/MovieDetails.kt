package com.tmdb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tmdb.R
import com.tmdb.ui.app.TmdbAppBar
import com.tmdb.ui.model.Movie

@Composable
fun MovieDetails(
    movie: Movie,
    navController: NavController
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TmdbAppBar() },
        content = {
            BodyContent(movie)
        }
    )
}

@Composable
fun BodyContent(movie: Movie) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(328.dp)
                .background(colorResource(id = R.color.black)),
            painter = rememberImagePainter(
                data = movie.posterPath,
                builder = {
                    placeholder(R.drawable.ic_broken_image)
                    error(R.drawable.ic_broken_image)
                },
            ),
            contentDescription = movie.title,
            contentScale = ContentScale.Fit,
        )
    }
}
