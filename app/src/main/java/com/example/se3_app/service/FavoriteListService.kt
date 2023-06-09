package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.api.ApiManager2
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post

class FavoriteListService {
    private val apiManager = ApiManager2()
    var errorMessage: String by mutableStateOf("")


    suspend fun getFavouriteList(userId: String? = null): List<ShoppingListDto> {
        val favoritList: List<ShoppingListDto> = apiManager.httpClient.get("favoritecocktail?userId=$userId")
        return favoritList
    }

    suspend fun addFavoritList(shoppingListDto: ShoppingListDto, userId: String? = null): ShoppingListDto {
        val favoritList: ShoppingListDto = apiManager.httpClient.post("favoritecocktail?userId=$userId") {
            body = shoppingListDto
        }
        return favoritList
    }

    suspend fun deleteFavoritList(userId: String?, element: CocktailDto) {
        apiManager.httpClient.delete<String>("favoritecocktail?userId=$userId")
    }

}