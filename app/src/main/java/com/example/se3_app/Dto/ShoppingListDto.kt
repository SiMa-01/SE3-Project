package com.example.se3_app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListDto(
    val _id: String?,
    val userId: String?,
    val list: MutableSet<String>?
)
