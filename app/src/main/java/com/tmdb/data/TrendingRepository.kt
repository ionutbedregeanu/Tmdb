package com.tmdb.data

import com.tmdb.cache.TmdbDatabase
import com.tmdb.cache.model.Movie
import com.tmdb.network.TrendingService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingService: TrendingService,
    private val database: TmdbDatabase
) {

    fun getTrendingMovies(mediaType: String, timeWindow: String): Observable<List<Movie>> =
        trendingService.getTrendingMovies(mediaType = mediaType, timeWindow = timeWindow)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                database.movieDao().insertMovies(movies = it.movies.filter { movie ->
                    movie.title.isNotEmpty() && movie.originalTitle.isNotEmpty()
                }.toCache())
            }
            .andThen(database.movieDao().getAllMovies())
            .startWith(database.movieDao().getAllMovies().take(1))
            .share()
}
