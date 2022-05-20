package com.tmdb.data

import com.tmdb.cache.TmdbDatabase
import com.tmdb.cache.model.Movie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(private val database: TmdbDatabase) {
    fun getMovieDetailsById(movieId: Int): Single<Movie> =
        database.movieDao().getMovieById(movieId)
            .subscribeOn(Schedulers.io())
            .onErrorReturnItem(Movie())
}
