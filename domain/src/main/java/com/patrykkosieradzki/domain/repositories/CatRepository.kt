package com.patrykkosieradzki.domain.repositories

interface CatRepository {
    suspend fun getRandomCatUrl(): String
}