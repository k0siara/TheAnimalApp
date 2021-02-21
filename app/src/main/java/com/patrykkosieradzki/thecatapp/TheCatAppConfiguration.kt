package com.patrykkosieradzki.thecatapp

import com.patrykkosieradzki.domain.AppConfiguration

class TheCatAppConfiguration : AppConfiguration {
    override val baseUrl: String = BuildConfig.API_URL

}