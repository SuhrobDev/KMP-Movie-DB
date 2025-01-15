package dev.soul.moviedbkmp.android.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.home.detail.MovieDetailIntent
import dev.soul.moviedbkmp.home.detail.MovieDetailViewModel

class AndroidMovieDetailViewModel(private val repository: HomeRepository) : ViewModel() {

    private val viewModel by lazy {
        MovieDetailViewModel(
            repository = repository,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.uiState

    fun onEvent(event: MovieDetailIntent) {
        viewModel.handleIntent(event)
    }
}