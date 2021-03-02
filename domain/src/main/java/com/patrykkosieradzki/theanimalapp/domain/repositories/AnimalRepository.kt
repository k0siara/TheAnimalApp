package com.patrykkosieradzki.theanimalapp.domain.repositories

import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData

interface AnimalRepository {
    suspend fun getAnimals(): List<AnimalData>
    suspend fun getRandomAnimalUrl(): String
}