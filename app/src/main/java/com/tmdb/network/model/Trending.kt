package com.tmdb.network.model

import com.google.gson.annotations.SerializedName

data class Trending(
    val page: Int = 0,
    @SerializedName("results")
    val movies: List<Movie> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResult: Int = 0
)
