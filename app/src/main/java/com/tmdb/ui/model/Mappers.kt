package com.tmdb.ui.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import com.tmdb.cache.model.Movie as MovieCacheModel
import com.tmdb.ui.model.Movie as MovieUIModel

fun List<MovieCacheModel>.toUI(configurationBaseUrl: String) = this.map { movieResponse ->
    MovieUIModel(
        posterPath = getPosterCompletePath(configurationBaseUrl = configurationBaseUrl, movieResponse.posterPath),
        adult = movieResponse.adult,
        overview = movieResponse.overview,
        releaseDate = getUIDate(movieResponse.releaseDate),
        genreIds = movieResponse.genreIds,
        id = movieResponse.id,
        originalLanguage = movieResponse.originalLanguage,
        title = if (movieResponse.title.isNotEmpty()) {
            movieResponse.title
        } else {
            movieResponse.originalTitle
        },
        backdropPath = movieResponse.backdropPath,
        popularity = movieResponse.popularity,
        voteCount = movieResponse.voteCount,
        video = movieResponse.video,
        voteAverage = movieResponse.voteAverage
    )
}

private fun getUIDate(date: String): String {
    val currentFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val displayFormat = SimpleDateFormat("MMM dd, yy", Locale.getDefault())
    return try {
        val formattedDate = currentFormat.parse(date)
        formattedDate?.let { displayFormat.format(it) } ?: ""
    } catch (e: ParseException) {
        ""
    }
}

private fun getPosterCompletePath(configurationBaseUrl: String, posterPath: String?) =
    posterPath?.let { configurationBaseUrl + it } ?: ""
