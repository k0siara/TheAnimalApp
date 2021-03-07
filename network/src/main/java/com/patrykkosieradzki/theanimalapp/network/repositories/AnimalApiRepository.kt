package com.patrykkosieradzki.theanimalapp.network.repositories

import com.patrykkosieradzki.theanimalapp.domain.model.AnimalBreed
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalCategory
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalWeight
import com.patrykkosieradzki.theanimalapp.domain.repositories.AnimalRepository
import com.patrykkosieradzki.theanimalapp.network.model.AnimalBreedResponse
import com.patrykkosieradzki.theanimalapp.network.model.AnimalCategoryResponse
import com.patrykkosieradzki.theanimalapp.network.model.AnimalResponse
import com.patrykkosieradzki.theanimalapp.network.model.AnimalWeightResponse
import com.patrykkosieradzki.theanimalapp.network.services.AnimalApiService
import com.patrykkosieradzki.theanimalapp.network.utils.NetworkHandler

class AnimalApiRepository(
    private val animalApiService: AnimalApiService,
    private val networkHandler: NetworkHandler
) : AnimalRepository {
    override suspend fun getAnimals(page: Int, limit: Int): List<AnimalData> {
        return networkHandler.safeNetworkCall {
            animalApiService.getAnimals(page, limit)
        }.toDomain()
    }

    override suspend fun getRandomAnimalUrl(): String {
        return networkHandler.safeNetworkCall {
            animalApiService.getRandomAnimal()
        }[0].url
    }
}

fun List<AnimalResponse>.toDomain(): List<AnimalData> = map {
    AnimalData(
        id = it.id,
        url = it.url,
        width = it.width,
        height = it.height,
        categories = it.categories.toDomain(),
        breeds = it.breeds.map { breed -> breed.toDomain() }
    )
}

@JvmName("toDomainAnimalCategoryResponse")
fun List<AnimalCategoryResponse>?.toDomain(): List<AnimalCategory> {
    return when {
        this == null -> emptyList()
        else -> map { category -> category.toDomain() }
    }
}

fun AnimalCategoryResponse.toDomain() = AnimalCategory(
    id = id,
    name = name
)

fun AnimalBreedResponse.toDomain() = AnimalBreed(
    weight = weight?.toDomain(),
    name = name,
    id = id,
    cfaUrl = cfaUrl,
    vetStreetUrl = vetStreetUrl,
    vcaHospitalsUrl = vcaHospitalsUrl,
    temperament = temperament,
    origin = origin,
    countryCodes = countryCodes,
    countryCode = countryCode,
    description = description,
    lifeSpan = lifeSpan,
    indoor = indoor,
    lap = lap,
    altNames = altNames,
    adaptability = adaptability,
    affectionLevel = affectionLevel,
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    energyLevel = energyLevel,
    grooming = grooming,
    healthIssues = healthIssues,
    intelligence = intelligence,
    sheddingLevel = sheddingLevel,
    socialNeeds = socialNeeds,
    strangerFriendly = strangerFriendly,
    vocalisation = vocalisation,
    experimental = experimental,
    hairless = hairless,
    natural = natural,
    rare = rare,
    rex = rex,
    suppressedTail = suppressedTail,
    shortLegs = shortLegs,
    wikipediaUrl = wikipediaUrl,
    hypoallergenic = hypoallergenic,
    referenceImageId = referenceImageId
)

fun AnimalWeightResponse.toDomain(): AnimalWeight {
    return AnimalWeight(
        imperial = imperial,
        metric = metric
    )
}
