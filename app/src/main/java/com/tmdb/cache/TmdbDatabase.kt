package com.tmdb.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tmdb.cache.dao.ConfigurationDao
import com.tmdb.cache.dao.MovieDao
import com.tmdb.cache.model.Configuration
import com.tmdb.cache.model.Movie

@Database(entities = [Movie::class, Configuration::class], version = 1, exportSchema = false)
@TypeConverters(TmdbTypeConverters::class)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun configurationDao(): ConfigurationDao
}
