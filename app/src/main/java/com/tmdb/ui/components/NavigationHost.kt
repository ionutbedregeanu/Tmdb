package com.tmdb.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tmdb.presentation.MovieDetailsViewModel
import com.tmdb.presentation.TrendingViewModel

sealed class NavRoutes(val route: String) {
    object Trending : NavRoutes("trending")
    object MovieDetails : NavRoutes("movieDetails/{movieId}")
}

@Composable
fun NavigationHost(trendingViewModel: TrendingViewModel, movieDetailsViewModel: MovieDetailsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Trending.route) {
        composable(NavRoutes.Trending.route) {
            Trending(
                trendingMovies = trendingViewModel.trendingMovies.value,
                error = trendingViewModel.error.value,
                navController = navController,
                onFilterSelected = { selectedFilter ->
                    trendingViewModel.updateTrendingFilter(selectedFilter.id)
                }
            )
        }
        composable(
            NavRoutes.MovieDetails.route,
            listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            loadMovieDetails(movieDetailsViewModel, backStackEntry)
            MovieDetails(
                movie = movieDetailsViewModel.selectedMovie.value,
                navController = navController
            )
        }
    }
}

private fun loadMovieDetails(movieDetailsViewModel: MovieDetailsViewModel, backStackEntry: NavBackStackEntry) {
    backStackEntry.arguments?.getInt("movieId")?.let { selectedMovieId ->
        movieDetailsViewModel.getMovieById(selectedMovieId)
    }
}
