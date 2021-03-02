package com.patrykkosieradzki.theanimalapp.ui.allanimals

import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState

class AllAnimalsViewModel : BaseViewModel<AllAnimalsViewState>(
    initialState = AllAnimalsViewState(inProgress = true)
) {
    override fun initialize() {

    }
}

data class AllAnimalsViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
