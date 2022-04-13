package com.tmdb.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.tmdb.data.ConfigurationRepository
import com.tmdb.data.TrendingRepository
import com.tmdb.ui.model.Movie
import com.tmdb.ui.model.Week
import com.tmdb.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

private const val MEDIA_TYPE = "all"

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

    private fun getTrendingMovies(trendingFilterId: String) =
        trendingRepository.getTrendingMovies(mediaType = MEDIA_TYPE, trendingFilterId).subscribeBy(
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
                getTrendingMovies(Week().id)
            }
        )
    }

    fun updateTrendingFilter(selectedFilterId: String) {
        getTrendingMovies(selectedFilterId)
    }
}
