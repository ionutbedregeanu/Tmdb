package com.tmdb.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tmdb.cache.dao.MovieDao
import com.tmdb.cache.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(TmdbTypeConverters::class)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
