package com.patrykkosieradzki.theanimalapp.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimalResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "categories")
    val categories: List<AnimalCategoryResponse>?,
    @Json(name = "breeds")
    val breeds: List<AnimalBreedResponse>
)

@JsonClass(generateAdapter = true)
data class AnimalCategoryResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class AnimalBreedResponse(
    @Json(name = "weight")
    val weight: AnimalWeightResponse?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "cfa_url")
    val cfaUrl: String?,
    @Json(name = "vetstreet_url")
    val vetStreetUrl: String?,
    @Json(name = "vcahospitals_url")
    val vcaHospitalsUrl: String?,
    @Json(name = "temperament")
    val temperament: String?,
    @Json(name = "origin")
    val origin: String?,
    @Json(name = "country_codes")
    val countryCodes: String?,
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "life_span")
    val lifeSpan: String?,
    @Json(name = "indoor")
    val indoor: Int?,
    @Json(name = "lap")
    val lap: Int?,
    @Json(name = "alt_names")
    val altNames: String?,
    @Json(name = "adaptability")
    val adaptability: Int?,
    @Json(name = "affection_level")
    val affectionLevel: Int?,
    @Json(name = "child_friendly")
    val childFriendly: Int?,
    @Json(name = "dog_friendly")
    val dogFriendly: Int?,
    @Json(name = "energy_level")
    val energyLevel: Int?,
    @Json(name = "grooming")
    val grooming: Int?,
    @Json(name = "health_issues")
    val healthIssues: Int?,
    @Json(name = "intelligence")
    val intelligence: Int?,
    @Json(name = "shedding_level")
    val sheddingLevel: Int?,
    @Json(name = "social_needs")
    val socialNeeds: Int?,
    @Json(name = "stranger_friendly")
    val strangerFriendly: Int?,
    @Json(name = "vocalisation")
    val vocalisation: Int?,
    @Json(name = "experimental")
    val experimental: Int?,
    @Json(name = "hairless")
    val hairless: Int?,
    @Json(name = "natural")
    val natural: Int?,
    @Json(name = "rare")
    val rare: Int?,
    @Json(name = "rex")
    val rex: Int?,
    @Json(name = "suppressed_tail")
    val suppressedTail: Int?,
    @Json(name = "short_legs")
    val shortLegs: Int?,
    @Json(name = "wikipedia_url")
    val wikipediaUrl: String?,
    @Json(name = "hypoallergenic")
    val hypoallergenic: Int?,
    @Json(name = "reference_image_id")
    val referenceImageId: String?
)

@JsonClass(generateAdapter = true)
data class AnimalWeightResponse(
    @Json(name = "imperial")
    val imperial: String?,
    @Json(name = "metric")
    val metric: String?
)
