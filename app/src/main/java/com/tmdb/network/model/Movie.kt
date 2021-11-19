package com.tmdb.network.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val adult: Boolean = false,
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    val id: Int = 0,
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    val title: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val popularity: Float = 0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0f
)
