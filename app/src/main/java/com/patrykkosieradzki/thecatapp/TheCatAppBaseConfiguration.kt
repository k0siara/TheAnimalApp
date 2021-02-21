package com.patrykkosieradzki.thecatapp

import com.patrykkosieradzki.domain.AppConfiguration

class TheCatAppBaseConfiguration : AppConfiguration {
    override val baseUrl: String = BuildConfig.API_URL

}