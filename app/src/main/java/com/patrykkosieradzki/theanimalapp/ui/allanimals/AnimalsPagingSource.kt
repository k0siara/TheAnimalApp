package com.patrykkosieradzki.theanimalapp.ui.allanimals

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.usecases.GetAnimalsUseCase
import timber.log.Timber

class AnimalsPagingSource(
    private val getAnimalsUseCase: GetAnimalsUseCase,
    private val startingPage: Int
) : PagingSource<Int, AnimalData>() {
    override fun getRefreshKey(state: PagingState<Int, AnimalData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimalData> {
        val pageNumber = params.key ?: startingPage
        Timber.d("Loading images... $pageNumber ${params.loadSize}")
        val result = getAnimalsUseCase.invoke(pageNumber, params.loadSize)
        return LoadResult.Page(
            data = result,
            prevKey = null, // Only paging forward.
            nextKey = pageNumber + 1
        )
    }
}
