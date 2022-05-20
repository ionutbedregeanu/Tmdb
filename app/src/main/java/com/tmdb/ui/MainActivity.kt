package com.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.tmdb.presentation.MovieDetailsViewModel
import com.tmdb.presentation.TrendingViewModel
import com.tmdb.ui.components.NavigationHost
import com.tmdb.ui.theme.TmdbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var trendingViewModel: TrendingViewModel
    private lateinit var moviewDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingViewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        moviewDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        setContent {
            TmdbTheme {
                NavigationHost(trendingViewModel, moviewDetailsViewModel)
            }
        }
    }
}
