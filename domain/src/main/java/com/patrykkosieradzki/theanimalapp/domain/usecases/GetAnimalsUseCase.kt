package com.patrykkosieradzki.theanimalapp.domain.usecases

import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.repositories.AnimalRepository

interface GetAnimalsUseCase {
    suspend fun invoke(page: Int, limit: Int): List<AnimalData>
}

class GetAnimalsUseCaseImpl(
    private val animalRepository: AnimalRepository
) : GetAnimalsUseCase {
    override suspend fun invoke(page: Int, limit: Int): List<AnimalData> {
        return animalRepository.getAnimals(page, limit)
    }
}
