package com.example.se3_app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class CocktailMetadataListDto (
    val data: List<CocktailMetadataDto>
    )

