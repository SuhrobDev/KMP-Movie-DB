package dev.soul.moviedbkmp.android.presentation.screen.detail

import dev.soul.moviedbkmp.data.network.dto.movie.actors.MovieActorsItem
import dev.soul.moviedbkmp.data.network.dto.movie.detail.MovieDetailModel
import dev.soul.moviedbkmp.data.network.dto.similar.SimilarMovieItem

data class MovieDetailState(
    val movieDetail: MovieDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
//    val selectedTab: Int = 0,
    val actors: List<MovieActorsItem> = emptyList(),
    val similarMovies: List<SimilarMovieItem> = emptyList()

)