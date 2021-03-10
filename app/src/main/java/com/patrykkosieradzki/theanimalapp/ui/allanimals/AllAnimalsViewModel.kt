package com.patrykkosieradzki.theanimalapp.ui.allanimals

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.GRID
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.LIST
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState
import com.patrykkosieradzki.theanimalapp.ui.utils.fireEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class AllAnimalsViewModel(
    private val getAnimalsUseCase: GetAnimalsUseCase
) : BaseViewModel<AllAnimalsViewState>(
    initialState = AllAnimalsViewState(inProgress = true)
) {
    val updateAnimalsEvent = LiveEvent<PagingData<AnimalData>>()
    val showAnimalDetailsEvent = LiveEvent<AnimalData>()

    val animals: Flow<PagingData<AnimalData>> = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = ANIMALS_PAGE_SIZE,
            initialLoadSize = ANIMALS_PAGE_SIZE,
            prefetchDistance = ANIMALS_PAGE_SIZE,
        ),
        pagingSourceFactory = { AnimalsPagingSource(getAnimalsUseCase) }
    ).flow

    override fun initialize() {
        safeLaunch {
            animals.collectLatest {
                updateAnimalsEvent.fireEvent(it)
            }
        }
    }

    fun onListGridSwitchClick() {
        updateViewState {
            it.copy(recyclerViewMode = if (state.recyclerViewMode == LIST) GRID else LIST)
        }
    }

    fun onAnimalItemClicked(item: AnimalData) {
        showAnimalDetailsEvent.fireEvent(item)
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
