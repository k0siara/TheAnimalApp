package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class ScreenshotsRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        arguments.putString(FILTER_BUNDLE_KEY, FlavorTestFilter::class.java.name)
        super.onCreate(arguments)
        Robot.screenshotsEnabled =
            "true" == arguments.getString("enableScreenshotsInAndroidTests", "false")
    }

    companion object {
        const val FILTER_BUNDLE_KEY = "filter"
    }
}
