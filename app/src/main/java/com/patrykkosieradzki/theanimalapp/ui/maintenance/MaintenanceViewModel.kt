package com.patrykkosieradzki.theanimalapp.ui.maintenance

import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState

class MaintenanceViewModel : BaseViewModel<MaintenanceViewState>(
    initialState = MaintenanceViewState()
) {
    fun setData(maintenanceData: MaintenanceData) {
        updateViewState {
            it.copy(
                title = maintenanceData.title,
                description = maintenanceData.description
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
