package com.patrykkosieradzki.theanimalapp.di

import com.patrykkosieradzki.theanimalapp.RemoteConfigManager
import com.patrykkosieradzki.theanimalapp.RemoteConfigManagerImpl
import com.patrykkosieradzki.theanimalapp.TheAnimalAppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCaseImpl
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCase
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCaseImpl
import com.patrykkosieradzki.theanimalapp.ui.allanimals.AllAnimalsViewModel
import com.patrykkosieradzki.theanimalapp.ui.launcher.LauncherViewModel
import com.patrykkosieradzki.theanimalapp.ui.maintenance.MaintenanceViewModel
import com.patrykkosieradzki.theanimalapp.ui.randomanimal.RandomAnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        TheAnimalAppConfiguration()
    }

    single {
        RemoteConfigManagerImpl(
            appConfiguration = get()
        )
    } bind RemoteConfigManager::class

    factory<GetRandomAnimalUrlUseCase> {
        GetRandomAnimalUrlUseCaseImpl(
            animalRepository = get()
        )
    }

    factory<GetAnimalsUseCase> {
        GetAnimalsUseCaseImpl(
            animalRepository = get()
        )
    }

    viewModel {
        LauncherViewModel(
            remoteConfigManager = get()
        )
    }

    viewModel {
        MaintenanceViewModel()
    }

    viewModel {
        RandomAnimalViewModel(
            getRandomAnimalUrlUseCase = get()
        )
    }

    viewModel {
        AllAnimalsViewModel(
            getAnimalsUseCase = get()
        )
    }
}
