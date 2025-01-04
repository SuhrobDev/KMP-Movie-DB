package dev.soul.moviedbkmp.android

import android.app.Application
import dev.soul.moviedbkmp.android.di.viewModelInjection
import dev.soul.moviedbkmp.di.startKoinForShared
import org.koin.android.ext.koin.androidContext

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinForShared {
            androidContext(this@MovieApp)
            modules(viewModelInjection)
        }
    }
}