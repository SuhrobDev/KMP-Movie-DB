package dev.soul.moviedbkmp.android.di

import dev.soul.moviedbkmp.android.presentation.screen.detail.MovieDetailViewModel
import dev.soul.moviedbkmp.android.presentation.screen.home.AndroidHomeViewModel
import org.koin.dsl.module

val viewModelInjection = module {
    factory { AndroidHomeViewModel(get()) }
//    viewModelOf(::AndroidHomeViewModel)

    factory { MovieDetailViewModel(get()) }
//    viewModelOf(::MovieDetailViewModel)

}