package com.tmdb.network

import com.tmdb.network.model.Configuration
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * Interface used by Retrofit in the api calls
 * [ConfigurationService] keeps the api methods specific to configuration
 */
interface ConfigurationService {

    @GET("configuration")
    fun getConfiguration(): Observable<Configuration>
}
