package com.tmdb.ui.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import com.tmdb.cache.model.Movie as MovieCacheModel
import com.tmdb.ui.model.Movie as MovieUIModel

fun List<MovieCacheModel>.toUI() = this.map { movieResponse ->
    movieResponse.toUI()
}

fun MovieCacheModel.toUI() = MovieUIModel(
    posterPath = posterPath,
    adult = adult,
    overview = overview,
    releaseDate = getUIDate(releaseDate),
    genreIds = genreIds,
    id = id,
    originalLanguage = originalLanguage,
    title = title.ifEmpty {
        originalTitle
    },
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)

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
