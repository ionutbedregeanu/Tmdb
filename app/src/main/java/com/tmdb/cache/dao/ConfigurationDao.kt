package com.tmdb.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.cache.model.Configuration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
abstract class ConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfiguration(config: Configuration): Completable

    @Query("SELECT baseUrl FROM configuration")
    abstract fun getBaseUrl(): Observable<String>
}
