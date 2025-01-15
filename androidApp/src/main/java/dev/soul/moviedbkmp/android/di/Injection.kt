package dev.soul.moviedbkmp.android.di

import dev.soul.moviedbkmp.android.presentation.screen.detail.AndroidMovieDetailViewModel
import dev.soul.moviedbkmp.android.presentation.screen.home.AndroidHomeViewModel
import org.koin.dsl.module

val viewModelInjection = module {
    factory { AndroidHomeViewModel(get()) }
//    viewModelOf(::AndroidHomeViewModel)

    factory { AndroidMovieDetailViewModel(get()) }
//    viewModelOf(::MovieDetailViewModel)

}