package com.patrykkosieradzki.theanimalapp.ui.animaldetails

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import com.patrykkosieradzki.theanimalapp.ui.allanimals.AnimalsPagingSource
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.ui.utils.ViewState
import com.patrykkosieradzki.theanimalapp.ui.utils.fireEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class AnimalDetailsViewModel(
    private val getAnimalsUseCase: GetAnimalsUseCase
) : BaseViewModel<AnimalDetailsViewState>(
    initialState = AnimalDetailsViewState(inProgress = true)
) {
    val updateAnimalsEvent = LiveEvent<PagingData<AnimalData>>()

    lateinit var animals: Flow<PagingData<AnimalData>>

    fun initLoading(startingPosition: Int) {
        animals = Pager(
            PagingConfig(
                enablePlaceholders = true,
                pageSize = 3,
                initialLoadSize = 5,
                prefetchDistance = 1,
            ),
            pagingSourceFactory = { AnimalsPagingSource(getAnimalsUseCase, startingPosition) }
        ).flow.cachedIn(viewModelScope)

        safeLaunch {
            animals.collectLatest {
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
