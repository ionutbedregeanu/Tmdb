package com.tmdb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.tmdb.ui.theme.tmdbTypography

@Composable
fun MovieDetails(
    movie: Movie,
    navController: NavController
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TmdbAppBar(navController = navController) },
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
        Text(
            text = movie.title,
            modifier = Modifier.padding(16.dp),
            style = tmdbTypography.movieDetailsTitle
        )
        Text(
            text = movie.overview,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            style = tmdbTypography.movieDetailsDescription
        )
    }
}
