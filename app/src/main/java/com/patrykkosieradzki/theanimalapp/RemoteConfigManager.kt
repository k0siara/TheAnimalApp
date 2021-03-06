package com.patrykkosieradzki.theanimalapp

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig

interface RemoteConfigManager {
    suspend fun isMaintenanceMode()
    val maintenanceEnabled: Boolean
    val maintenanceTitle: String
    val maintenanceDescription: String
}

class RemoteConfigManagerImpl(
    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
) : RemoteConfigManager {
    init {
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    override val maintenanceEnabled = remoteConfig.getBoolean("is_maintenance_mode")
    override val maintenanceTitle = remoteConfig.getString("maintenance_title")
    override val maintenanceDescription = remoteConfig.getString("maintenance_description")

    override suspend fun isMaintenanceMode() {
        TODO("Not yet implemented")
    }

}

