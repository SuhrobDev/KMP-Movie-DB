package dev.soul.moviedbkmp.di

import dev.soul.moviedbkmp.domain.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinDep :KoinComponent{
    val homeRepository : HomeRepository by inject()
}