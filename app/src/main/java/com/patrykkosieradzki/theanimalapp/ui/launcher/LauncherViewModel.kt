package com.patrykkosieradzki.theanimalapp.ui.launcher

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.RemoteConfigManager
import com.patrykkosieradzki.theanimalapp.ui.maintenance.MaintenanceData
import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState
import com.patrykkosieradzki.theanimalapp.utils.extensions.fireEvent

class LauncherViewModel(
    private val remoteConfigManager: RemoteConfigManager
) : BaseViewModel<LauncherViewState>(
    initialState = LauncherViewState()
) {
    val showBlockingMaintenanceModeScreenEvent = LiveEvent<MaintenanceData>()
    val goToDesktopEvent = LiveEvent<Unit>()

    override fun initialize() {
        super.initialize()
        safeLaunch {
            remoteConfigManager.checkMaintenanceMode(
                onCompleteCallback = { isMaintenanceMode ->
                    if (isMaintenanceMode) {
                        showBlockingMaintenanceModeScreenEvent.fireEvent(
                            MaintenanceData(
                                title = remoteConfigManager.maintenanceTitle,
                                description = remoteConfigManager.maintenanceDescription
                            )
                        )
                    } else {
                        goToDesktopEvent.fireEvent()
                    }
                },
                onFailureCallback = {
                }
            )
        }
    }
}

data class LauncherViewState(
    override val inProgress: Boolean = false,
    val title: String = "",
    val description: String = ""
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
