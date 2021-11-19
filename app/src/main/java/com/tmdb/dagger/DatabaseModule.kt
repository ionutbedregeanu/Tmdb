package com.tmdb.dagger

import android.app.Application
import androidx.room.Room
import com.tmdb.cache.TmdbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides all dependencies from local database
 */

private const val DATABASE_NAME = "tmdb.db"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideGopDatabase(application: Application): TmdbDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            TmdbDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigrationFrom(1)
            .build()
}
