package dev.soul.moviedbkmp.home.presentation

sealed class HomeIntent {
    object GetNowPlaying : HomeIntent()
//    object GetMovieDetail : HomeIntent()
    object GetPeople : HomeIntent()
    object GetNowPlayingMovies : HomeIntent()
    object GetPopularMovies : HomeIntent()
}