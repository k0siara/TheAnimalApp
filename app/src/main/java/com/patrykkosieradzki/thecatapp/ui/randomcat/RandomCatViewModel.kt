package com.patrykkosieradzki.thecatapp.ui.randomcat

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.domain.usecases.GetRandomCatUrlUseCase
import com.patrykkosieradzki.thecatapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.thecatapp.ui.utils.ViewState
import com.patrykkosieradzki.thecatapp.ui.utils.fireEvent

class RandomCatViewModel(
    private val getRandomCatUrlUseCase: GetRandomCatUrlUseCase
) : BaseViewModel<RandomCatViewState>(
    initialState = RandomCatViewState(inProgress = true)
) {
    val loadCatImageEvent = LiveEvent<String>()

    override fun initialize() {
        loadRandomCat()
    }

    private fun loadRandomCat() {
        updateViewState { it.copy(isImageLoadingVisible = true) }
        safeLaunch {
            val catUrl = getRandomCatUrlUseCase.invoke()
            loadCatImageEvent.fireEvent(catUrl)
        }
    }

    fun onLoadMoreButtonClicked() {
        loadRandomCat()
    }

    fun onCatImageLoadedSuccessfully() {
        updateViewState { it.copy(isImageLoadingVisible = false) }
    }
}

data class RandomCatViewState(
    override val inProgress: Boolean,
    val isImageLoadingVisible: Boolean = true
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}