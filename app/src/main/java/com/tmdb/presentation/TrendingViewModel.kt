package com.tmdb.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tmdb.data.ConfigurationRepository
import com.tmdb.data.TrendingRepository
import com.tmdb.ui.model.Movie
import com.tmdb.ui.model.TmdbError
import com.tmdb.ui.model.Week
import com.tmdb.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

private const val MEDIA_TYPE = "all"
private const val POSTER_SIZE = "w300"

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository,
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {
    var trendingMovies = mutableStateOf<List<Movie>>(listOf())
        private set

    var error = mutableStateOf(TmdbError())
        private set

    private var configurationBaseUrl = ""

    init {
        getConfigurationBaseUrl()
    }

    private fun getTrendingMovies(trendingFilterId: String) =
        trendingRepository.getTrendingMovies(mediaType = MEDIA_TYPE, trendingFilterId).subscribeBy(
            onNext = { movies ->
                trendingMovies.value = movies.toUI(configurationBaseUrl)
            },
            onError = {
                error.value = TmdbError(enabled = true)
            }
        )

    private fun getConfigurationBaseUrl(selectedFilterId: String = Week().id) {
        configurationRepository.getConfigurationBaseUrl().subscribeBy(
            onNext = { baseUrl ->
                configurationBaseUrl = baseUrl + POSTER_SIZE
                getTrendingMovies(selectedFilterId)
            },
            onError = {
                error.value = TmdbError(enabled = true)
            }
        )
    }

    fun updateTrendingFilter(selectedFilterId: String) {
        if (configurationBaseUrl == POSTER_SIZE) {
            getConfigurationBaseUrl(selectedFilterId)
        } else {
            getTrendingMovies(selectedFilterId)
        }
    }
}
