package dev.soul.moviedbkmp.home.detail

sealed class MovieDetailIntent {
    data class Detail(val movieId: Long) : MovieDetailIntent()
    data class SimilarMovies(val movieId: Long) : MovieDetailIntent()
    data class Actors(val movieId: Long) : MovieDetailIntent()
//    data class onTabChange(val tab: Int) : MovieDetailIntent()
}