package dev.soul.moviedbkmp.home.presentation

import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.shared.toCommonStateFlow
import dev.soul.moviedbkmp.utils.onError
import dev.soul.moviedbkmp.utils.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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
        }
    }

    private fun getNowPlaying() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(
            nowPlaying = repository.getNowPlaying()
        )
    }

    private fun getPeople() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            repository.getPeople().onSuccess {
                _uiState.value = _uiState.value.copy(
                    people = it.results ?: emptyList(),
                    isLoading = false
                )
            }.onError {
                _uiState.value = _uiState.value.copy(
                    error = it.name,
                    isLoading = false
                )
            }
        }
    }
}