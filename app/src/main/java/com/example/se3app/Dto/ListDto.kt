package com.example.se3app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class ListDto(
    val _id: String?,
    val userId: String?,
    val type: String?,
    val list: Array<String?>?
)
