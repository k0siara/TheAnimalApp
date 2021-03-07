package com.patrykkosieradzki.theanimalapp.domain.model

data class AnimalData(
    val id: String,
    val url: String?,
    val width: Int,
    val height: Int,
    val categories: List<AnimalCategory>,
    val breeds: List<AnimalBreed>
)

data class AnimalCategory(
    val id: Int,
    val name: String
)

data class AnimalBreed(
    val weight: AnimalWeight?,
    val name: String?,
    val id: String?,
    val cfaUrl: String?,
    val vetStreetUrl: String?,
    val vcaHospitalsUrl: String?,
    val temperament: String?,
    val origin: String?,
    val countryCodes: String?,
    val countryCode: String?,
    val description: String?,
    val lifeSpan: String?,
    val indoor: Int?,
    val lap: Int?,
    val altNames: String?,
    val adaptability: Int?,
    val affectionLevel: Int?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val energyLevel: Int?,
    val grooming: Int?,
    val healthIssues: Int?,
    val intelligence: Int?,
    val sheddingLevel: Int?,
    val socialNeeds: Int?,
    val strangerFriendly: Int?,
    val vocalisation: Int?,
    val experimental: Int?,
    val hairless: Int?,
    val natural: Int?,
    val rare: Int?,
    val rex: Int?,
    val suppressedTail: Int?,
    val shortLegs: Int?,
    val wikipediaUrl: String?,
    val hypoallergenic: Int?,
    val referenceImageId: String?
)

data class AnimalWeight(
    val imperial: String?,
    val metric: String?
)
