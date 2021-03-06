package com.patrykkosieradzki.theanimalapp.network.repositories

import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.repositories.AnimalRepository
import com.patrykkosieradzki.theanimalapp.network.services.AnimalApiService

class AnimalApiRepository(
    private val animalApiService: AnimalApiService
) : AnimalRepository {
    override suspend fun getAnimals(): List<AnimalData> {
        return emptyList()
    }

    override suspend fun getRandomAnimalUrl(): String {
        return animalApiService.getRandomAnimal()[0].url
    }
}