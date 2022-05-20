package com.tmdb.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tmdb.data.MovieDetailsRepository
import com.tmdb.ui.model.Movie
import com.tmdb.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsRepository: MovieDetailsRepository) : ViewModel() {
    val selectedMovie = mutableStateOf(Movie())
    fun getMovieById(id: Int) {
        movieDetailsRepository.getMovieDetailsById(id).subscribeBy(
            onSuccess = { movie ->
                selectedMovie.value = movie.toUI()
            },
            onError = {

            }
        )
    }
}
