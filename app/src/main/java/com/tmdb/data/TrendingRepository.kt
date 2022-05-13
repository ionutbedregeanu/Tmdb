package com.tmdb.data

import com.tmdb.cache.TmdbDatabase
import com.tmdb.cache.model.Movie as CacheMovieModel
import com.tmdb.network.model.Movie as NetworkMovieModel
import com.tmdb.network.TrendingService
import com.tmdb.network.model.Trending
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingService: TrendingService,
    private val database: TmdbDatabase
) {

    fun getTrendingMovies(mediaType: String, timeWindow: String): Observable<List<CacheMovieModel>> =
        trendingService.getTrendingMovies(mediaType = mediaType, timeWindow = timeWindow)
            .subscribeOn(Schedulers.io())
            .onErrorReturnItem(Trending())
            .flatMapCompletable { trending -> insertTrendingMovies(trending.movies, timeWindow) }
            .andThen(database.movieDao().getMoviesByTimeWindow(timeWindow))
            .startWith(database.movieDao().getMoviesByTimeWindow(timeWindow).take(1))
            .share()

    private fun insertTrendingMovies(movies: List<NetworkMovieModel>, timeWindow: String) =
        database.movieDao().insertMovies(movies = movies
            .filter { movie ->
                movie.title.isNotEmpty() && movie.originalTitle.isNotEmpty()
            }
            .toCache()
            .map { movie -> movie.copy(trending = timeWindow) }
        )
}
