package com.tmdb.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val posterPath: String? = null,
    val adult: Boolean = false,
    val overview: String = "",
    val releaseDate: String = "",
    val genreIds: List<Int>? = listOf(),
    @PrimaryKey
    val id: Int = 0,
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val title: String = "",
    val backdropPath: String? = null,
    val popularity: Float = 0f,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val voteAverage: Float = 0f
)
