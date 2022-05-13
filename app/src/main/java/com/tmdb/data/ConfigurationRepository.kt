package com.tmdb.data

import com.tmdb.cache.TmdbDatabase
import com.tmdb.network.ConfigurationService
import com.tmdb.network.model.Configuration
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val configurationService: ConfigurationService,
    private val database: TmdbDatabase
) {

    fun getConfigurationBaseUrl(): Observable<String> =
        configurationService.getConfiguration()
            .subscribeOn(Schedulers.io())
            .onErrorReturnItem(Configuration())
            .flatMapCompletable {
                database.configurationDao().insertConfiguration(it.toCache())
            }
            .andThen(database.configurationDao().getBaseUrl())
}
