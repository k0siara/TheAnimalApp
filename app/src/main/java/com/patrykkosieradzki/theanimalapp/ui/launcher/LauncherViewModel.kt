package com.patrykkosieradzki.theanimalapp.ui.launcher

import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState

class LauncherViewModel : BaseViewModel<LauncherViewState>(
    initialState = LauncherViewState()
)

data class LauncherViewState(
    override val inProgress: Boolean = false,
    val title: String = "",
    val description: String = ""
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
