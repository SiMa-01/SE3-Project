package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.api.ApiManager2
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post

class ShoppingListService {
    private val apiManager = ApiManager2()
    var errorMessage: String by mutableStateOf("")

    suspend fun getShoppingList(userId: String? = null): List<ShoppingListDto> {
        val shoppingList: List<ShoppingListDto> = apiManager.httpClient.get("shoppinglist?userId=$userId")
        return shoppingList
    }

    suspend fun addShoppingList(shoppingListDto: ShoppingListDto, userId: String? = null): ShoppingListDto {
        val shoppingList: ShoppingListDto = apiManager.httpClient.post("shoppinglist?userId=$userId") {
            body = shoppingListDto
        }
        return shoppingList
    }

    suspend fun deleteShoppingList(userId: String? = null, element: String? = null) {
        apiManager.httpClient.delete<String>("shoppinglist/listelement?userId=$userId")
    }
}