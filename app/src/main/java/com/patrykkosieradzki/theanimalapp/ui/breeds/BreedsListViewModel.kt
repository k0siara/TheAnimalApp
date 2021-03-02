package com.patrykkosieradzki.theanimalapp.ui.breeds

import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState

class BreedsListViewModel : BaseViewModel<BreedsListViewState>(
    initialState = BreedsListViewState(true)
) {

}

data class BreedsListViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}