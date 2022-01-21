package com.tmdb.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.tmdb.data.ConfigurationRepository
import com.tmdb.data.TrendingRepository
import com.tmdb.ui.model.Movie
import com.tmdb.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository,
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {
    var trendingMovies = mutableStateListOf<Movie>()
        private set

    private var configurationBaseUrl = ""

    init {
        getConfigurationBaseUrl()
    }

    private fun getTrendingMovies() = trendingRepository.getTrendingMovies("all", "week").subscribeBy(
        onNext = { movies ->
            trendingMovies.clear()
            trendingMovies.addAll(movies.toUI(configurationBaseUrl))
        }
    )

    private fun getConfigurationBaseUrl() {
        configurationRepository.getConfigurationBaseUrl().subscribeBy(
            onNext = { baseUrl ->
                val imageSize = "w300"
                configurationBaseUrl = baseUrl + imageSize
                getTrendingMovies()
            }
        )
    }
}
