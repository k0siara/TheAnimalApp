package com.patrykkosieradzki.thecatapp.ui.breeds

import com.patrykkosieradzki.thecatapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.thecatapp.ui.utils.ViewState

class BreedsListViewModel : BaseViewModel<BreedsListViewState>(
    initialState = BreedsListViewState(true)
) {

}

data class BreedsListViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}