package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.FavoriteCocktailDto
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.api.ApiManager2
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.delay

class FavoriteListService {
    private val apiManager = ApiManager2()
    var errorMessage: String by mutableStateOf("")

    suspend fun addFavoriteUser(userId: String?) {
        val favoritList: FavoriteCocktailDto = apiManager.httpClient.post(
            "favoritecocktail?userId=$userId"
        )
    }

    suspend fun getFavouriteList(userId: String? ): List<FavoriteCocktailDto> {
        println("Hallo ich bin hier 3 " + userId)
        val variable = "favoritecocktail?userId=$userId"
        val favoritList: List<FavoriteCocktailDto> = apiManager.httpClient.get(
            variable
        )
        delay(4000)
        println("Hallo ich bin hier 4 "  + favoritList[0])

        return favoritList
    }

    suspend fun addFavoritList(cocktailDto: CocktailDto, userId: String? = null): FavoriteCocktailDto {
        val favoriteCocktailDto: FavoriteCocktailDto = apiManager.httpClient.post(
            "favoritecocktail/listelement?userId=$userId"
        ) {
            body = cocktailDto
        }
        return favoriteCocktailDto
    }

    suspend fun deleteFavoritList(userId: String?, cocktailId: String?) {
        val favoritList: ShoppingListDto = apiManager.httpClient.delete(
            "favoritecocktail/listelement?userId=$userId&cocktailId=$cocktailId"
        )
    }
}
