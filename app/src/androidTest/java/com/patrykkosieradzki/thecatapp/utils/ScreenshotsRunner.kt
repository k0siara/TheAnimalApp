package com.patrykkosieradzki.thecatapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.testing.screenshot.ScreenshotRunner

class ScreenshotsRunner : AndroidJUnitRunner() {
    private var wakeLock: PowerManager.WakeLock? = null

    override fun onCreate(args: Bundle) {
        super.onCreate(args)
        Robot.shot = "true" == args.getString("shot", "false")
        if (Robot.shot) {
            ScreenshotRunner.onCreate(this, args)
        }
    }

    override fun finish(resultCode: Int, results: Bundle) {
        if (Robot.shot) {
            ScreenshotRunner.onDestroy()
        }
        super.finish(resultCode, results)
    }

    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    override fun onStart() {
        val app = targetContext.applicationContext
        val name: String = ScreenshotsRunner::class.java.simpleName
        val power = app.getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = power.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE,
            name
        ).apply {
            acquire()
        }
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        wakeLock!!.release()
    }
}