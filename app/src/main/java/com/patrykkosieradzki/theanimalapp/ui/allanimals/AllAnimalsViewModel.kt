package com.patrykkosieradzki.theanimalapp.ui.allanimals

import androidx.paging.PagingData
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.GRID
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.LIST
import com.patrykkosieradzki.theanimalapp.ui.list.SharedAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState
import com.patrykkosieradzki.theanimalapp.ui.utils.fireEvent
import kotlinx.coroutines.flow.collectLatest

class AllAnimalsViewModel(
    private val sharedAnimalFlowRepository: SharedAnimalFlowRepository
) : BaseViewModel<AllAnimalsViewState>(
    initialState = AllAnimalsViewState(inProgress = true)
) {
    val updateAnimalsEvent = LiveEvent<PagingData<AnimalData>>()
    val showAnimalDetailsEvent = LiveEvent<Int>()

    override fun initialize() {
        safeLaunch {
            sharedAnimalFlowRepository.animals.collectLatest {
                updateAnimalsEvent.fireEvent(it)
            }
        }
    }

    fun onListGridSwitchClick() {
        updateViewState {
            it.copy(recyclerViewMode = if (state.recyclerViewMode == LIST) GRID else LIST)
        }
    }

    fun onAnimalItemClicked(position: Int) {
        showAnimalDetailsEvent.fireEvent(position)
    }

    companion object {
        const val ANIMALS_PAGE_SIZE = 10
    }
}

data class AllAnimalsViewState(
    override val inProgress: Boolean,
    val recyclerViewMode: RecyclerViewMode = LIST
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}

enum class RecyclerViewMode {
    LIST, GRID
}
