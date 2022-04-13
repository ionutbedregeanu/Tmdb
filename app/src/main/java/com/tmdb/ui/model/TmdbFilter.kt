package com.tmdb.ui.model

import com.tmdb.R

interface TmdbFilter {
    val id: String
    val valueResourceId: Int
}

data class Day(
    override val id: String = "day",
    override val valueResourceId: Int = R.string.time_window_today
) : TmdbFilter

data class Week(
    override val id: String = "week",
    override val valueResourceId: Int = R.string.time_window_week
) : TmdbFilter
