package com.example.se3_app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCocktailDto(
    val _id: String?,
    val userId: String?,
    val list: MutableList<CocktailDto>
)
