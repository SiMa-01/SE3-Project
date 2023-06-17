package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.api.ApiManager
import io.ktor.client.request.get
import io.ktor.client.request.post

class CocktailService {
    var apiManager = ApiManager()
    var errorMessage: String by mutableStateOf("")

    suspend fun findCocktails(
        name: String? = null,
        taste: String? = null,
        ingredients: List<String> = emptyList(),
        alcoholic: Boolean? = null,
        difficulty: String? = null
    ): List<CocktailDto> {
        var stringURL = "cocktails?"

        if (!name.isNullOrBlank()) {
            stringURL = "${stringURL}name=$name&"
        }
        if (!taste.isNullOrBlank()) {
            stringURL = "${stringURL}taste=$taste&"
        }
        if (!ingredients.isNullOrEmpty()) {
            stringURL = "${stringURL}ingredients="
            for (element in ingredients) {
                stringURL = "$stringURL$element,"
            }
            stringURL = stringURL.substring(0, stringURL.length - 1)
            stringURL = "$stringURL&"
        }
        if (alcoholic != null) {
            stringURL = "${stringURL}alcoholic=$alcoholic&"
        }
        if (!difficulty.isNullOrBlank()) {
            stringURL = "${stringURL}difficulty=$difficulty&"
        }
        stringURL = stringURL.substring(0, stringURL.length - 1)

        val cocktails: List<CocktailDto> = apiManager.httpClient.get(stringURL)
        return cocktails
    }

    suspend fun getIngredients(): List<String> {
        val stringUrl = "ingredients"
        val ingredients: List<String> = apiManager.httpClient.get(stringUrl)
        return ingredients
    }

    suspend fun getTastes(): List<String> {
        val stringUrl = "tastes"
        val tastes: List<String> = apiManager.httpClient.get(stringUrl)
        return tastes
    }

    suspend fun addCocktail(cocktailDto: CocktailDto): CocktailDto {
        val cocktail: CocktailDto = apiManager.httpClient.post("cocktails") {
            body = cocktailDto
        }
        return cocktail
    }
}
