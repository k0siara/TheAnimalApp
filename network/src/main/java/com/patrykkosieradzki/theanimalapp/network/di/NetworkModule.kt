package com.patrykkosieradzki.theanimalapp.network.di

import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration
import com.patrykkosieradzki.theanimalapp.domain.repositories.AnimalRepository
import com.patrykkosieradzki.theanimalapp.network.repositories.AnimalApiRepository
import com.patrykkosieradzki.theanimalapp.network.services.AnimalApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<AppConfiguration>().baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<AnimalApiService> {
        get<Retrofit>().create(AnimalApiService::class.java)
    }

    single<AnimalRepository> {
        AnimalApiRepository(get())
    }
}