package com.patrykkosieradzki.theanimalapp.ui.animaldetails

import androidx.paging.PagingData
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.list.SharedAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState
import com.patrykkosieradzki.theanimalapp.ui.utils.fireEvent
import kotlinx.coroutines.flow.collectLatest

class AnimalDetailsViewModel(
    private val sharedAnimalFlowRepository: SharedAnimalFlowRepository
) : BaseViewModel<AnimalDetailsViewState>(
    initialState = AnimalDetailsViewState(inProgress = true)
) {
    val updateAnimalsEvent = LiveEvent<PagingData<AnimalData>>()

    override fun initialize() {
        super.initialize()
        safeLaunch {
            sharedAnimalFlowRepository.animals.collectLatest {
                updateAnimalsEvent.fireEvent(it)
            }
        }
    }
}

data class AnimalDetailsViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
