package com.patrykkosieradzki.network.repositories

import com.patrykkosieradzki.domain.repositories.CatRepository
import com.patrykkosieradzki.network.services.CatApiService

class CatApiRepository(
    private val catApiService: CatApiService
) : CatRepository {
    override suspend fun getRandomCatUrl(): String {
        return catApiService.getRandomCat()[0].url
    }
}