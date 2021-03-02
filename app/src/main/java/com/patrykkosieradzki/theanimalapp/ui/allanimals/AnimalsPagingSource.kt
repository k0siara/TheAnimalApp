package com.patrykkosieradzki.theanimalapp.ui.allanimals

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData

class AnimalsPagingSource(

) : PagingSource<Int, AnimalData>() {
    override fun getRefreshKey(state: PagingState<Int, AnimalData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimalData> {
        TODO("Not yet implemented")
    }

}