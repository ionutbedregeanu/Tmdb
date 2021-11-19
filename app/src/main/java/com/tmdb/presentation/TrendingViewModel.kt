package com.tmdb.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tmdb.data.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(val trendingRepository: TrendingRepository) : ViewModel() {
    fun getTrendingMovies() = trendingRepository.getTrendingMovies("all", "week").subscribeBy(
        onNext = {
            item -> Log.d("Blabla","Elements: $item")
        },
        onError = { Log.d("Blabla","Error: $it") }
    )
}
