package com.tmdb.data

import com.tmdb.cache.TmdbDatabase
import com.tmdb.network.TrendingService
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val REQUEST_TIME_OUT = 10L

class TrendingRepository @Inject constructor(
    private val trendingService: TrendingService,
    private val database: TmdbDatabase
) {

    fun getTrendingMovies(mediaType: String, timeWindow: String) =
        trendingService.getTrendingMovies(mediaType = mediaType, timeWindow = timeWindow)
            .retryWhen { it.delay(REQUEST_TIME_OUT, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                database.movieDao().insertMovies(movies = it.movies.toCache()) }
            .andThen(database.movieDao().getAllMovies())
            .startWith(database.movieDao().getAllMovies().take(1))
            .share()
}
