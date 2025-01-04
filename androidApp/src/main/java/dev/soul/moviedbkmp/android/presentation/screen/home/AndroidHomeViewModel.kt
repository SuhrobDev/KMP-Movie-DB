package dev.soul.moviedbkmp.android.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.home.presentation.HomeIntent
import dev.soul.moviedbkmp.home.presentation.HomeViewModel

class AndroidHomeViewModel(private val repository: HomeRepository) : ViewModel() {

//    private val _uiState = MutableStateFlow(HomeState())
//    val uiState: StateFlow<HomeState> get() = _uiState
//
//    fun handleIntent(intent: HomeIntent) {
//        when (intent) {
//            is HomeIntent.GetNowPlaying -> getNowPlaying()
////            is HomeIntent.GetMovieDetail -> getMovieDetail()
//            is HomeIntent.GetPeople -> getPeople()
//        }
//    }
//
//    private fun getNowPlaying() = viewModelScope.launch {
//        _uiState.value = _uiState.value.copy(
//            nowPlaying = repository.getNowPlaying()
//        )
//    }
////    private fun getMovieDetail() = viewModelScope.launch {
////        _uiState.value = _uiState.value.copy(
////            nowPlaying = repository.getMovieDetail()
////        )
////    }
//
//    private fun getPeople() = viewModelScope.launch {
//        _uiState.value = _uiState.value.copy(
//            isLoading = true
//        )
//        viewModelScope.launch {
//            repository.getPeople().onSuccess {
//                _uiState.value = _uiState.value.copy(
//                    people = it.results ?: emptyList(),
//                    isLoading = false
//                )
//            }.onError {
//                _uiState.value = _uiState.value.copy(
//                    error = it.name,
//                    isLoading = false
//                )
//            }
//        }
//    }


    private val viewModel by lazy {
        HomeViewModel(
            repository = repository,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.uiState

    fun onEvent(event: HomeIntent) {
        viewModel.handleIntent(event)
    }
}