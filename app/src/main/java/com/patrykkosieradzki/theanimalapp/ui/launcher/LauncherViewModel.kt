package com.patrykkosieradzki.theanimalapp.ui.launcher

import com.patrykkosieradzki.theanimalapp.RemoteConfigManager
import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState

class LauncherViewModel(
    private val remoteConfigManager: RemoteConfigManager
) : BaseViewModel<LauncherViewState>(
    initialState = LauncherViewState()
) {

    override fun initialize() {
        super.initialize()
        safeLaunch {
            remoteConfigManager.checkMaintenanceMode(
                onCompleteCallback = { isMaintenanceMode ->
                    if (isMaintenanceMode) {
                        navigateTo(LauncherFragmentDirections.toMaintenanceFragment())
                    } else {
                        navigateTo(LauncherFragmentDirections.toAllAnimalsFragment())
                    }
                },
                onFailureCallback = {}
            )
        }
    }
}

data class LauncherViewState(
    override val inProgress: Boolean = false
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
