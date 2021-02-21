package com.patrykkosieradzki.thecatapp.ui.randomcat

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.domain.usecases.GetRandomCatUrlUseCase
import com.patrykkosieradzki.thecatapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.thecatapp.ui.utils.ViewState

class RandomCatViewModel(
    private val getRandomCatUrlUseCase: GetRandomCatUrlUseCase
) : BaseViewModel<RandomCatViewState>(
    initialState = RandomCatViewState(inProgress = true)
) {
    val loadRandomCatEvent = LiveEvent<Unit>()

    override fun initialize() {
        loadRandomCat()
    }

    fun loadRandomCat() {
        safeLaunch {
            val catUrl = getRandomCatUrlUseCase.invoke()
        }
    }
}

data class RandomCatViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}