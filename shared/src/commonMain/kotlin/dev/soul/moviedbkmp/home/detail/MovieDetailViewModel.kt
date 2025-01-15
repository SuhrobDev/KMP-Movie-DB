package dev.soul.moviedbkmp.home.detail

import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.shared.toCommonStateFlow
import dev.soul.moviedbkmp.utils.onError
import dev.soul.moviedbkmp.utils.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    val repository: HomeRepository,
    coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow(MovieDetailState())
    val uiState = _uiState.toCommonStateFlow()

    fun handleIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.Detail -> movieDetail(intent.movieId)
            is MovieDetailIntent.SimilarMovies -> similarMovies(intent.movieId)


            is MovieDetailIntent.Actors -> {
//                actors(intent.movieId)
            }
        }
    }

    private fun movieDetail(id: Long) {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            repository.getMovieDetail(id).onSuccess { data ->
                _uiState.update { it.copy(isLoading = false, movieDetail = data) }
                similarMovies(id)
            }.onError { error ->
                _uiState.update { it.copy(isLoading = false, error = error.name) }
            }
        }
    }

    private fun actors(movieId: Long) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getMovieActors(movieId).onSuccess { data ->
                _uiState.update { it.copy(isLoading = false, actors = data.results ?: emptyList()) }
            }.onError { error ->
                _uiState.update { it.copy(isLoading = false, error = error.name) }
            }
        }
    }

    private fun similarMovies(movieId: Long) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getSimilarMovies(movieId).onSuccess { data ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        similarMovies = data.results ?: emptyList()
                    )
                }
            }.onError { error ->
                _uiState.update { it.copy(isLoading = false, error = error.name) }
            }
        }
    }
}