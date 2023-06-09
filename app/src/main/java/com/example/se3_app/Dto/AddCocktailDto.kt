package com.example.se3_app.Dto

data class AddCocktailDto(
    val name: String?,
    val ingredients: Array<String>?,
    val difficulty: String?,
    val alcoholic: Boolean?,
    val taste: String?,
    val preparation: String?
)
