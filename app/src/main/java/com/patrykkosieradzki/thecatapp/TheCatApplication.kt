package com.patrykkosieradzki.thecatapp

import android.app.Application
import com.patrykkosieradzki.network.di.networkModule
import com.patrykkosieradzki.thecatapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TheCatApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@TheCatApplication)
            modules(networkModule, appModule)
        }
    }
}