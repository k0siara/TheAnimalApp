package com.patrykkosieradzki.theanimalapp.ui.list.details

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.list.SharedAnimalFlowRepository
import com.patrykkosieradzki.theanimalapp.utils.BaseViewModel
import com.patrykkosieradzki.theanimalapp.utils.ViewState
import com.patrykkosieradzki.theanimalapp.utils.extensions.fireChange
import kotlinx.coroutines.flow.collectLatest

class AnimalDetailsViewModel(
    private val sharedAnimalFlowRepository: SharedAnimalFlowRepository
) : BaseViewModel<AnimalDetailsViewState>(
    initialState = AnimalDetailsViewState(inProgress = true)
) {
    val collectedAnimals = MutableLiveData<PagingData<AnimalData>>()

    override fun initialize() {
        super.initialize()
        safeLaunch {
            sharedAnimalFlowRepository.animals.collectLatest {
                collectedAnimals.fireChange(it)
            }
        }
    }
}

data class AnimalDetailsViewState(
    override val inProgress: Boolean
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
