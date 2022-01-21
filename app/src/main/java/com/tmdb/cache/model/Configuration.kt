package com.tmdb.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Configuration(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val baseUrl: String
)
