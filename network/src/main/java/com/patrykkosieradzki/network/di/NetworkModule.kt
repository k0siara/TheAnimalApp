package com.patrykkosieradzki.network.di

import com.patrykkosieradzki.domain.AppConfiguration
import com.patrykkosieradzki.domain.TheCatAppBaseConfiguration
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<AppConfiguration>().baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
//            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            .client(get())
            .build()
    }
}