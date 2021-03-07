package com.patrykkosieradzki.theanimalapp.network.services

import com.patrykkosieradzki.theanimalapp.network.model.AnimalResponse
import com.patrykkosieradzki.theanimalapp.network.utils.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimalApiService {
    @GET("images/search")
    suspend fun getRandomAnimal(): ApiResult<List<RandomAnimalResponse>>

    @GET("images/search")
    suspend fun getAnimals(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ApiResult<List<AnimalResponse>>
}

data class RandomAnimalResponse(
    val url: String
)



