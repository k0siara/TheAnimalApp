package com.patrykkosieradzki.theanimalapp.ui.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import com.patrykkosieradzki.theanimalapp.ui.allanimals.AllAnimalsViewModel
import com.patrykkosieradzki.theanimalapp.ui.allanimals.AnimalsPagingSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow

interface SharedAnimalFlowRepository {
    val animals: Flow<PagingData<AnimalData>>
}

class SharedPagingAnimalFlowRepository(
    private val getAnimalsUseCase: GetAnimalsUseCase
) : SharedAnimalFlowRepository {

    override val animals: Flow<PagingData<AnimalData>> = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = AllAnimalsViewModel.ANIMALS_PAGE_SIZE,
            initialLoadSize = AllAnimalsViewModel.ANIMALS_PAGE_SIZE,
            prefetchDistance = AllAnimalsViewModel.ANIMALS_PAGE_SIZE,
        ),
        pagingSourceFactory = { AnimalsPagingSource(getAnimalsUseCase, startingPage = 0) }
    ).flow.cachedIn(GlobalScope)

}