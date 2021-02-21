package com.patrykkosieradzki.network.services

import retrofit2.http.GET

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCat(): List<RandomCatResponse>
}

data class RandomCatResponse(
    val url: String
)