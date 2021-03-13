package com.patrykkosieradzki.theanimalapp.ui.maintenance

import com.patrykkosieradzki.theanimalapp.RemoteConfigManager
import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState

class MaintenanceViewModel(
    private val remoteConfigManager: RemoteConfigManager
) : BaseViewModel<MaintenanceViewState>(
    initialState = MaintenanceViewState()
) {
    override fun initialize() {
        updateViewState {
            it.copy(
                title = remoteConfigManager.maintenanceTitle,
                description = remoteConfigManager.maintenanceDescription
            )
        }
    }
}

data class MaintenanceViewState(
    override val inProgress: Boolean = false,
    val title: String = "",
    val description: String = ""
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
