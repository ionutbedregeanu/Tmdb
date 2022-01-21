package com.tmdb.data

import com.tmdb.network.model.Configuration
import com.tmdb.network.model.Movie
import com.tmdb.cache.model.Movie as MovieCacheModel
import com.tmdb.cache.model.Configuration as ConfigurationCacheModel

fun List<Movie>.toCache() = this.map { movieResponse ->
    MovieCacheModel(
        posterPath = movieResponse.posterPath,
        adult = movieResponse.adult,
        overview = movieResponse.overview,
        releaseDate = movieResponse.releaseDate,
        genreIds = movieResponse.genreIds,
        id = movieResponse.id,
        originalTitle = movieResponse.originalTitle,
        originalLanguage = movieResponse.originalLanguage,
        title = movieResponse.title,
        backdropPath = movieResponse.backdropPath,
        popularity = movieResponse.popularity,
        voteCount = movieResponse.voteCount,
        video = movieResponse.video,
        voteAverage = movieResponse.voteAverage
    )
}

fun Configuration.toCache() = ConfigurationCacheModel(baseUrl = this.images.baseUrl)
