package com.example.se3_app.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.se3_app.Dto.ListDto
import com.example.se3_app.api.ApiManager2
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post

class ListService {
    private val apiManager = ApiManager2()
    var errorMessage: String by mutableStateOf("")

    suspend fun getShoppingList(userId: String? = null): List<ListDto> {
        val shoppingList: List<ListDto> = apiManager.httpClient.get("shoppinglist?id=$userId")
        return shoppingList
    }

    suspend fun getFavouriteList(userId: String? = null): List<ListDto> {
        val favoritList: List<ListDto> = apiManager.httpClient.get("favoritlist?id=$userId")
        return favoritList
    }

    suspend fun addShoppingList(listDto: ListDto): ListDto {
        val shoppingList: ListDto = apiManager.httpClient.post("shoppinglist/add") {
            body = listDto
        }
        return shoppingList
    }

    suspend fun addFavoritList(listDto: ListDto): ListDto {
        val favoritList: ListDto = apiManager.httpClient.post("favoritlist/add") {
            body = listDto
        }
        return favoritList
    }

    suspend fun deleteShoppingList(/*keine Ahnung was ich da übergeben soll*/) {
        apiManager.httpClient.delete<String>("shoppinglist/remove")
    }


    suspend fun deleteFavoritList(/*keine Ahnung was ich da übergeben soll*/) {
        apiManager.httpClient.delete<String>("favoritlist/remove")
    }

}