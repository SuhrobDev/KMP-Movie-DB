package dev.soul.moviedbkmp.home.presentation

import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.shared.toCommonStateFlow
import dev.soul.moviedbkmp.utils.onError
import dev.soul.moviedbkmp.utils.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
    coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.toCommonStateFlow()

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.GetNowPlaying -> getNowPlaying()
            is HomeIntent.GetPeople -> getPeople()
            is HomeIntent.GetNowPlayingMovies -> getNowPlayingMovies()
        }
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            repository.getNowPlayingMovies().onSuccess { success ->
                _uiState.update {
                    it.copy(isLoading = false, nowPlayingMovies = success.results ?: emptyList())
                }
            }.onError { error ->
                _uiState.update {
                    it.copy(isLoading = false, error = error.name)
                }
            }
        }
    }

    private fun getNowPlaying() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                nowPlaying = repository.getNowPlaying()
            )
        }
    }

    private fun getPeople() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            repository.getPeople().onSuccess { success ->
                _uiState.update {
                    it.copy(isLoading = false, people = success.results ?: emptyList())
                }
            }.onError { error ->
                _uiState.update {
                    it.copy(isLoading = false, error = error.name)
                }
            }
        }
    }
}