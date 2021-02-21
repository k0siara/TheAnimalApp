package com.patrykkosieradzki.domain.repositories

interface CatRepository {
    fun getRandomCatUrl(): String
}