package com.patrykkosieradzki.theanimalapp.ui.settings

import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState

class SettingsViewModel : BaseViewModel<SettingsViewState>(
    initialState = SettingsViewState(inProgress = false)
)

data class SettingsViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
