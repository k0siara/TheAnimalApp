package com.patrykkosieradzki.theanimalapp.domain.repositories

interface AnimalRepository {
    suspend fun getRandomAnimalUrl(): String
}