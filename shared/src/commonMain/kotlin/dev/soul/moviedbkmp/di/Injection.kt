package dev.soul.moviedbkmp.di

import dev.soul.moviedbkmp.data.impl.HomeRepositoryImpl
import dev.soul.moviedbkmp.data.network.InsultCensorClient
import dev.soul.moviedbkmp.data.network.createHttpClient
import dev.soul.moviedbkmp.data.network.createJson
import dev.soul.moviedbkmp.data.paging.HomePagingSource
import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.home.presentation.HomeViewModel
import dev.soul.moviedbkmp.shared.platformModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun startKoinForShared(
    appDeclaration: KoinAppDeclaration = {}
) {
    println("Initializing Koin")
    startKoin {
        appDeclaration()
        modules(
            commonModule(),
            platformModule()
        )
    }
    println("Koin initialized successfully")
}

fun initKoin() = startKoinForShared {  }

fun commonModule() = module {
    single { createJson() }
    single { InsultCensorClient(get()) }
    single { createHttpClient(get()) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single<HomeRepository> { HomeRepositoryImpl(get()) }
//    factory { HomePagingSource(get()) }
//    factory { HomeViewModel(get(),get()) }
}