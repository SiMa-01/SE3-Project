package com.example.se3app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class CocktailDto(
    val _id: String?,
    val name: String?,
    val ingredients: Array<String>?,
    val difficulty: String?,
    val alcoholic: Boolean,
    val taste: String?
)
