package com.patrykkosieradzki.theanimalapp.di

import com.patrykkosieradzki.theanimalapp.TheAnimalAppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCase
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCaseImpl
import com.patrykkosieradzki.theanimalapp.ui.launcher.LauncherViewModel
import com.patrykkosieradzki.theanimalapp.ui.maintenance.MaintenanceViewModel
import com.patrykkosieradzki.theanimalapp.ui.randomanimal.RandomAnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        TheAnimalAppConfiguration()
    }

    factory<GetRandomAnimalUrlUseCase> {
        GetRandomAnimalUrlUseCaseImpl(get())
    }

    viewModel {
        LauncherViewModel()
    }

    viewModel {
        MaintenanceViewModel()
    }

    viewModel {
        RandomAnimalViewModel(get())
    }
}
