package dev.soul.moviedbkmp.home.presentation

import androidx.paging.PagingData
import androidx.paging.map
import dev.soul.moviedbkmp.data.network.dto.movie.MovieItemModel
import dev.soul.moviedbkmp.data.network.dto.people.PeopleItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

data class HomeState(
    val isLoading: Boolean = false,
    val nowPlaying: Flow<PagingData<MovieItemModel>>? = flowOf(PagingData.empty()),
    val error: String? = null,
    val people: List<PeopleItemModel> = emptyList(),
    val nowPlayingMovies: List<MovieItemModel> = emptyList(),
    val popularMovies: List<MovieItemModel> = emptyList()
)

//fun <T : Any> Flow<PagingData<T>>.toListFlow(): List<T> {
//    return map { pagingData ->
//        val items = mutableListOf<T>()
//        pagingData.map { item ->
//            items.add(item)
//        }
//        items
//    }
//}