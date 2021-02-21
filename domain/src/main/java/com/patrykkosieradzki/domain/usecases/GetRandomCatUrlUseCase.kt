package com.patrykkosieradzki.domain.usecases

import com.patrykkosieradzki.domain.repositories.CatRepository

interface GetRandomCatUrlUseCase {
    suspend fun invoke(): String
}

class GetRandomCatUrlUseCaseImpl(
    private val catRepository: CatRepository
) : GetRandomCatUrlUseCase {

    override suspend fun invoke(): String {
        return catRepository.getRandomCatUrl()
    }
}