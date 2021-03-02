package com.patrykkosieradzki.theanimalapp.utils

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestForFlavors(
    val flavors: Array<String> = [CATS, DOGS]
)

const val CATS = "cats"
const val DOGS = "dogs"