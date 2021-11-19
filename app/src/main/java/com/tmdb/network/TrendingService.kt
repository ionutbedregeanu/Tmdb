package com.tmdb.network

import com.tmdb.network.model.Trending
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface used by Retrofit in the api calls
 * [TrendingService] keeps the api methods specific to trending
 */
interface TrendingService {
    @GET("trending/{media_type}/{time_window}")
    fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): Observable<Trending>
}
