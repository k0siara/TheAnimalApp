package com.patrykkosieradzki.theanimalapp.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.patrykkosieradzki.theanimalapp.RemoteConfigManager
import com.patrykkosieradzki.theanimalapp.RemoteConfigManagerImpl
import com.patrykkosieradzki.theanimalapp.TheAnimalAppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCaseImpl
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCase
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCaseImpl
import com.patrykkosieradzki.theanimalapp.ui.launcher.LauncherViewModel
import com.patrykkosieradzki.theanimalapp.ui.list.SharedAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.ui.list.SharedPagingAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.ui.list.all.AllAnimalsViewModel
import com.patrykkosieradzki.theanimalapp.ui.list.details.AnimalDetailsViewModel
import com.patrykkosieradzki.theanimalapp.ui.maintenance.MaintenanceViewModel
import com.patrykkosieradzki.theanimalapp.ui.randomanimal.RandomAnimalViewModel
import com.patrykkosieradzki.theanimalapp.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        TheAnimalAppConfiguration()
    }

    single {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return@single EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } bind SharedPreferences::class

//    factory {
//        SecureSimpleDataSerializer(
//            crypto = get()
//        )
//    }

//    single {
//        androidContext().createDataStore(
//            fileName = "AndroidDataStore.pb",
//            serializer = get<SecureSimpleDataSerializer>()
//        )
//    }

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

    single<SharedAnimalFlowRepository> {
        SharedPagingAnimalFlowRepository(
            getAnimalsUseCase = get()
        )
    }

    viewModel {
        LauncherViewModel(
            remoteConfigManager = get()
        )
    }

    viewModel {
        MaintenanceViewModel(
            remoteConfigManager = get()
        )
    }

    viewModel {
        RandomAnimalViewModel(
            getRandomAnimalUrlUseCase = get()
        )
    }

    viewModel {
        AllAnimalsViewModel(
            sharedAnimalFlowRepository = get()
        )
    }

    viewModel {
        AnimalDetailsViewModel(
            sharedAnimalFlowRepository = get()
        )
    }

    viewModel {
        SettingsViewModel()
    }
}
