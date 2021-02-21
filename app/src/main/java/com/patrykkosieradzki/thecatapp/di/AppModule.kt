package com.patrykkosieradzki.thecatapp.di

import com.patrykkosieradzki.domain.AppConfiguration
import com.patrykkosieradzki.domain.usecases.GetRandomCatUrlUseCase
import com.patrykkosieradzki.domain.usecases.GetRandomCatUrlUseCaseImpl
import com.patrykkosieradzki.thecatapp.TheCatAppConfiguration
import com.patrykkosieradzki.thecatapp.ui.randomcat.RandomCatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        TheCatAppConfiguration()
    }

    factory<GetRandomCatUrlUseCase> {
        GetRandomCatUrlUseCaseImpl(get())
    }

    viewModel {
        RandomCatViewModel(get())
    }
}