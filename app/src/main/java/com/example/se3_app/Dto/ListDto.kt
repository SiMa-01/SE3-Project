package com.example.se3_app.Dto

import kotlinx.serialization.Serializable

@Serializable
data class ListDto(
    val _id: String?,
    val userId: String?,
    val type: String?,
    val list: Array<String?>?
)
