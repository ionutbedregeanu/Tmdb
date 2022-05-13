package com.tmdb.ui.model

import com.tmdb.R

data class TmdbError(
    val enabled: Boolean = false,
    val messageId: Int = R.string.trending_error_message
)
