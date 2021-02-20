package com.patrykkosieradzki.thecatapp

import android.app.Application
import com.patrykkosieradzki.thecatapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheCatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TheCatApplication)
            modules(appModule)
        }
    }
}