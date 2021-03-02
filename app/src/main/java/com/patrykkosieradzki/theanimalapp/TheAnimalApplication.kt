package com.patrykkosieradzki.theanimalapp

import android.app.Application
import com.patrykkosieradzki.theanimalapp.di.appModule
import com.patrykkosieradzki.theanimalapp.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TheAnimalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@TheAnimalApplication)
            modules(networkModule, appModule)
        }
    }
}
