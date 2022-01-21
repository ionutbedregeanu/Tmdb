package com.tmdb.ui.trending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.tmdb.presentation.TrendingViewModel
import com.tmdb.ui.theme.TmdbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingActivity : ComponentActivity() {
    private lateinit var trendingViewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingViewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        setContent {
            TmdbTheme {
                TrendingLayout(trendingViewModel.trendingMovies)
            }
        }
    }
}
