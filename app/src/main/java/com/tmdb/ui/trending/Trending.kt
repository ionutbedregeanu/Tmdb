package com.tmdb.ui.trending

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.tmdb.R
import com.tmdb.ui.app.TmdbAppBar
import com.tmdb.ui.model.Movie
import com.tmdb.ui.model.TmdbError
import com.tmdb.ui.model.TmdbFilter
import com.tmdb.ui.theme.tmdbColors
import com.tmdb.ui.theme.tmdbTypography

private const val NOT_A_NUMBER = "NaN"

@Composable
fun TrendingLayout(
    trendingMovies: List<Movie>,
    error: TmdbError,
    onFilterSelected: (selectedFilter: TmdbFilter) -> Unit
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TmdbAppBar() },
        content = {
            BodyContent(trendingMovies, error, onFilterSelected = onFilterSelected)
        }
    )
}

@Composable
fun BodyContent(movies: List<Movie>, error: TmdbError, onFilterSelected: (selectedFilter: TmdbFilter) -> Unit) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        TrendingFilters(modifier = Modifier.padding(top = 16.dp), onFilterSelected = onFilterSelected)
        if (error.enabled) {
            if (movies.isEmpty()) {
                EmptyTrendingContent()
            } else {
                TrendingContent(movies)
            }
        } else {
            TrendingContent(movies)
        }
    }
}

@Composable
fun EmptyTrendingContent() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            style = tmdbTypography.materialTypography.subtitle2,
            text = stringResource(id = R.string.trending_error_message)
        )
    }
}

@Composable
fun TrendingContent(movies: List<Movie>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = tmdbColors.cardColor,
        modifier = Modifier
            .width(250.dp)
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            ConstraintLayout(modifier = Modifier.padding(bottom = 16.dp)) {
                val (poster, rating) = createRefs()
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(328.dp)
                        .constrainAs(poster) {},
                    painter = rememberImagePainter(
                        data = movie.posterPath,
                        builder = {
                            placeholder(R.drawable.ic_broken_image)
                            error(R.drawable.ic_broken_image)
                        },
                    ),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                )

                val ratingValue = if (movie.voteAverage > 0) {
                    movie.voteAverage.toString()
                } else {
                    NOT_A_NUMBER
                }
                CircleRating(
                    modifier = Modifier.constrainAs(rating) {
                        top.linkTo(poster.bottom)
                        start.linkTo(poster.start, margin = 16.dp)
                        bottom.linkTo(poster.bottom)
                    },
                    ratingValue
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = movie.title,
                style = tmdbTypography.cardTitle
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = movie.releaseDate,
                style = tmdbTypography.cardReleaseDate
            )
        }
    }
}

@Composable
private fun CircleRating(modifier: Modifier, rating: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(tmdbColors.ratingBorder, shape = CircleShape)
            .padding(1.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(tmdbColors.ratingBackground, shape = CircleShape)
                .layout { measurable, constraints ->
                    // Measure the composable
                    val placeable = measurable.measure(constraints)

                    //get the current max dimension to assign width=height
                    val currentHeight = placeable.height
                    var heightCircle = currentHeight
                    if (placeable.width > heightCircle)
                        heightCircle = placeable.width

                    //assign the dimension and the center position
                    layout(heightCircle, heightCircle) {
                        // Where the composable gets placed
                        placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                    }
                }
        ) {

            Text(
                text = rating,
                textAlign = TextAlign.Center,
                color = tmdbColors.textColor,
                modifier = Modifier
                    .padding(8.dp)
                    .defaultMinSize(20.dp)
            )
        }
    }
}
