package com.tmdb.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.cache.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<Movie>): Completable

    @Query("SELECT * FROM movies WHERE trending = :timeWindow")
    abstract fun getMoviesByTimeWindow(timeWindow: String): Observable<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun getMovieById(id: Int): Single<Movie>
}
