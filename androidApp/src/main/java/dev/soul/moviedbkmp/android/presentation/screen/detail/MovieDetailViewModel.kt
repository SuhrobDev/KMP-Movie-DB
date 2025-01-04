package dev.soul.moviedbkmp.android.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.utils.onError
import dev.soul.moviedbkmp.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(val repository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailState())
    val uiState: StateFlow<MovieDetailState> get() = _uiState

    fun handleIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.Detail -> movieDetail(intent.movieId)
            is MovieDetailIntent.SimilarMovies -> {
                similarMovies(intent.movieId)
            }

            is MovieDetailIntent.Actors -> {
//                actors(intent.movieId)
            }

//            is MovieDetailIntent.onTabChange -> {
//                onTabChange(intent.tab)
//            }
        }
    }

//    private fun onTabChange(tab: Int) {
//        _uiState.value = _uiState.value.copy(selectedTab = tab)
//    }

    private fun movieDetail(id: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getMovieDetail(id).onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, movieDetail = it)
                similarMovies(id)
            }.onError {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.name)
            }
        }
    }

    private fun actors(movieId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getMovieActors(movieId).onSuccess {
                _uiState.value =
                    _uiState.value.copy(isLoading = false, actors = it.results ?: emptyList())

            }.onError {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.name)
            }
        }
    }

    private fun similarMovies(movieId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getSimilarMovies(movieId).onSuccess {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        similarMovies = it.results ?: emptyList()
                    )
            }.onError {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.name)
            }
        }
    }
}