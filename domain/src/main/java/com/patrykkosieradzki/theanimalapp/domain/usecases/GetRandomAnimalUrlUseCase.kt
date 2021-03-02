package com.patrykkosieradzki.theanimalapp.domain.usecases

import com.patrykkosieradzki.theanimalapp.domain.repositories.AnimalRepository

interface GetRandomAnimalUrlUseCase {
    suspend fun invoke(): String
}

class GetRandomAnimalUrlUseCaseImpl(
    private val animalRepository: AnimalRepository
) : GetRandomAnimalUrlUseCase {

    override suspend fun invoke(): String {
        return animalRepository.getRandomAnimalUrl()
    }
}