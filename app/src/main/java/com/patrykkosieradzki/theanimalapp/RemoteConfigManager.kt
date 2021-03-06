package com.patrykkosieradzki.theanimalapp

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.patrykkosieradzki.theanimalapp.domain.AppConfiguration
import timber.log.Timber
import java.util.concurrent.TimeUnit

interface RemoteConfigManager {
    suspend fun checkMaintenanceMode(
        onCompleteCallback: (Boolean) -> Unit,
        onFailureCallback: (Exception) -> Unit
    )
    val maintenanceEnabled: Boolean
    val maintenanceTitle: String
    val maintenanceDescription: String
}

class RemoteConfigManagerImpl(
    private val appConfiguration: AppConfiguration,
    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
) : RemoteConfigManager {

    override val maintenanceEnabled = remoteConfig[IS_MAINTENANCE_MODE_KEY].asBoolean()
    override val maintenanceTitle = remoteConfig[MAINTENANCE_TITLE_KEY].asString()
    override val maintenanceDescription = remoteConfig[MAINTENANCE_DESCRIPTION_KEY].asString()

    override suspend fun checkMaintenanceMode(
        onCompleteCallback: (Boolean) -> Unit,
        onFailureCallback: (Exception) -> Unit
    ) {
        fetchAndActivate(
            if (appConfiguration.debug) INSTANT else TWELVE_HOURS,
            onCompleteCallback, onFailureCallback
        )
    }

    private fun fetchAndActivate(
        minimumFetchIntervalInSeconds: Long,
        onCompleteCallback: (Boolean) -> Unit,
        onFailureCallback: (Exception) -> Unit
    ) {
        val remoteConfigSettings = remoteConfigSettings {
            setMinimumFetchIntervalInSeconds(minimumFetchIntervalInSeconds)
            fetchTimeoutInSeconds = FETCH_TIMEOUT_IN_SECONDS
        }
        remoteConfig.setConfigSettingsAsync(remoteConfigSettings).addOnCompleteListener {
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener {
                    Timber.d("Remote Config fetched successfully")
                    onCompleteCallback.invoke(maintenanceEnabled)
                }
                .addOnFailureListener {
                    Timber.e(it, "Failure during Remote Config fetch")
                    onFailureCallback.invoke(it)
                }
        }
    }

    companion object {
        const val IS_MAINTENANCE_MODE_KEY = "is_maintenance_mode"
        const val MAINTENANCE_TITLE_KEY = "maintenance_title"
        const val MAINTENANCE_DESCRIPTION_KEY = "maintenance_description"
        const val FETCH_TIMEOUT_IN_SECONDS = 5L
        val TWELVE_HOURS = TimeUnit.HOURS.toSeconds(12)
        const val INSTANT = 0L
    }
}
