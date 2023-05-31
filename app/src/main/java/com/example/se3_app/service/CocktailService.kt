package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.CocktailMetadataDto
import com.example.se3_app.api.ApiService
import io.ktor.client.request.get
import io.ktor.client.request.post

class CocktailService {
    private val apiManager = ApiService()
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    suspend fun findCocktails(
        name: String?,
        taste: String?,
        ingredients: List<String>?,
        alcoholic: Boolean?,
        difficulty: String?
    ): List<CocktailDto> {
        var stringURL = "cocktails?"

        if (!name.isNullOrBlank()) {
            stringURL = "$stringURL+name=$name&"
        }
        if (!taste.isNullOrBlank()) {
            stringURL = "$stringURL+taste=$taste&"
        }
        if (!ingredients.isNullOrEmpty()) {
            stringURL = "$stringURL+ingredients="
            for (element in ingredients) {
                stringURL = "$stringURL$element,"
            }
            stringURL = stringURL.substring(0, stringURL.length - 1)
            stringURL = "$stringURL&"
        }
        if (alcoholic != null) {
            stringURL = "$stringURL+alcoholic=$alcoholic&"
        }
        if (!difficulty.isNullOrBlank()) {
            stringURL = "$stringURL+difficulty=$difficulty&"
        }
        stringURL = stringURL.substring(0, stringURL.length - 1)

        return apiManager.httpClient.get("$stringURL")
    }

    suspend fun addCocktail(cocktailDto: CocktailDto): CocktailDto{
        val cocktail: CocktailDto = apiManager.httpClient.post("add/") {
            body = cocktailDto
        }
        return cocktail
    }
}
