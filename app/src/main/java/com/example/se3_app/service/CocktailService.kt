package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.CocktailMetadataDto
import com.example.se3_app.Dto.CocktailMetadataListDto
import com.example.se3_app.api.ApiService
import io.ktor.client.features.get
import io.ktor.client.request.get
import io.ktor.client.request.post

class CocktailService {
    private val apiManager = ApiService()
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    suspend fun getAllCocktails(): List<CocktailMetadataDto> {
        val metadataDto: CocktailMetadataListDto = apiManager.httpClient.get("cocktails/")

        return metadataDto.data
    }

    suspend fun getCocktailById(cocktailId: Int): CocktailDto {
        val metadataDto: CocktailMetadataDto = apiManager.httpClient.get("cocktails/id/$cocktailId")

        return metadataDto.data
    }

    suspend fun getCocktailByName(cocktailName: String): CocktailDto {
        val metadataDto: CocktailMetadataDto = apiManager.httpClient.get("cocktails/name/$cocktailName")

        return metadataDto.data
    }

    suspend fun getCocktailByTaste(cocktailTaste: String): CocktailDto {
        val metadataDto: CocktailMetadataDto = apiManager.httpClient.get("cocktails/taste/$cocktailTaste")

        return metadataDto.data
    }

    suspend fun getCocktailByAlcoholic(cocktailAlcoholic: Boolean): CocktailDto {
        val metadataDto: CocktailMetadataDto = apiManager.httpClient.get("cocktails/alcoholic/$cocktailAlcoholic")

        return metadataDto.data
    }

    suspend fun addCocktail(cocktailDto: CocktailDto): CocktailDto {
        val metadataDto: CocktailMetadataDto = apiManager.httpClient.post("add/") {
            body = cocktailDto
        }

        return metadataDto.data
    }
}