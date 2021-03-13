package com.patrykkosieradzki.theanimalapp.ui.list.all

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.list.SharedAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.ui.list.all.RecyclerViewMode.GRID
import com.patrykkosieradzki.theanimalapp.ui.list.all.RecyclerViewMode.LIST
import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState
import com.patrykkosieradzki.theanimalapp.utils.extensions.fireChange
import kotlinx.coroutines.flow.collectLatest

class AllAnimalsViewModel(
    private val sharedAnimalFlowRepository: SharedAnimalFlowRepository
) : BaseViewModel<AllAnimalsViewState>(
    initialState = AllAnimalsViewState(inProgress = true)
) {
    val collectedAnimalsEvent = MutableLiveData<PagingData<AnimalData>>()

    override fun initialize() {
        safeLaunch {
            sharedAnimalFlowRepository.animals.collectLatest {
                collectedAnimalsEvent.fireChange(it)
            }
        }
    }

    fun onListGridSwitchClick() {
        updateViewState {
            it.copy(recyclerViewMode = if (state.recyclerViewMode == LIST) GRID else LIST)
        }
    }

    fun onAnimalItemClicked(position: Int) {
        navigateTo(AllAnimalsFragmentDirections.toAnimalDetailsFragment(position))
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
