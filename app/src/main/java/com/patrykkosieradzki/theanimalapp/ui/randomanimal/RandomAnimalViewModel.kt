package com.patrykkosieradzki.theanimalapp.ui.randomanimal

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetRandomAnimalUrlUseCase
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState
import com.patrykkosieradzki.theanimalapp.ui.utils.fireEvent

class RandomAnimalViewModel(
    private val getRandomAnimalUrlUseCase: GetRandomAnimalUrlUseCase
) : BaseViewModel<RandomAnimalViewState>(
    initialState = RandomAnimalViewState(inProgress = true)
) {
    val loadAnimalImageEvent = LiveEvent<String>()

    override fun initialize() {
        loadRandomAnimal()
    }

    private fun loadRandomAnimal() {
        updateViewState { it.copy(isImageLoadingVisible = true) }
        safeLaunch {
            val catUrl = getRandomAnimalUrlUseCase.invoke()
            loadAnimalImageEvent.fireEvent(catUrl)
        }
    }

    fun onLoadMoreButtonClicked() {
        loadRandomAnimal()
    }

    fun onAnimalImageLoadedSuccessfully() {
        updateViewState { it.copy(isImageLoadingVisible = false) }
    }
}

data class RandomAnimalViewState(
    override val inProgress: Boolean,
    val isImageLoadingVisible: Boolean = true
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}