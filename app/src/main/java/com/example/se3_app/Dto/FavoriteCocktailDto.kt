package com.example.se3_app.Dto

data class FavoriteCocktailDto(
    val _id: String?,
    val userId: String?,
    val list: MutableList<CocktailDto>
)
