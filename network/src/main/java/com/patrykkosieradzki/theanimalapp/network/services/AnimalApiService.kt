package com.patrykkosieradzki.theanimalapp.network.services

import retrofit2.http.GET

interface AnimalApiService {
    @GET("images/search")
    suspend fun getRandomAnimal(): List<RandomAnimalResponse>
}

data class RandomAnimalResponse(
    val url: String
)