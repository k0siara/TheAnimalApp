package com.patrykkosieradzki.thecatapp.di

import com.patrykkosieradzki.domain.AppConfiguration
import com.patrykkosieradzki.thecatapp.TheCatAppBaseConfiguration
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        TheCatAppBaseConfiguration()
    }

}