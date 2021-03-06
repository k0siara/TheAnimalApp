package com.patrykkosieradzki.theanimalapp

import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration

class TheAnimalAppConfiguration : AppConfiguration {
    override val debug: Boolean = BuildConfig.DEBUG
    override val baseUrl: String = BuildConfig.API_URL
}
