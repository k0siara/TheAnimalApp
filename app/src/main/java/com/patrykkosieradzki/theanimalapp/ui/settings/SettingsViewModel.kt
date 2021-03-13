package com.patrykkosieradzki.theanimalapp.ui.settings

import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState

class SettingsViewModel : BaseViewModel<SettingsViewState>(
    initialState = SettingsViewState(inProgress = false)
)

data class SettingsViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
