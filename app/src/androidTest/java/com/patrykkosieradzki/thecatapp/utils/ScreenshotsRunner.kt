package com.patrykkosieradzki.thecatapp.utils

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class ScreenshotsRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)
        Robot.screenshotsEnabled = "true" == arguments.getString("enableScreenshotsInAndroidTests", "false")
    }
}