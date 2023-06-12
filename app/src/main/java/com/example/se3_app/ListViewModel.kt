package com.example.se3_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.FavoriteCocktailDto
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.service.FavoriteListService
import com.example.se3_app.service.ShoppingListService
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var userId: String = ""

    private val favoriteListService = FavoriteListService()
    private val shoppingListService = ShoppingListService()
    var userFavoriteList: MutableList<FavoriteCocktailDto> by mutableStateOf(mutableListOf())
    var userShoppingList: MutableList<ShoppingListDto> by mutableStateOf(mutableListOf())
    var itemsInFavoriteList: MutableList<CocktailDto> by mutableStateOf(mutableListOf())


    fun getFavouriteList(userId: String?) {
        viewModelScope.launch {
            loading = true
            try {
                userFavoriteList = favoriteListService.getFavouriteList(userId).toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    fun addFavoritList(userId: String?, cocktailDto: CocktailDto) {
        viewModelScope.launch {
            loading = true
            try {
                val addedFavorite = favoriteListService.addFavoritList(cocktailDto, userId)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    fun deleteFavoritList(userId: String?, cocktailId: String?) {
        viewModelScope.launch {
            loading = true
            try {
                favoriteListService.deleteFavoritList(userId, cocktailId)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------

    fun getShoppingList(userId: String?) {
        viewModelScope.launch {
            loading = true
            try {
                userShoppingList = shoppingListService.getShoppingList(userId).toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    fun addShoppingList(userId: String?, element: String?) {
        viewModelScope.launch {
            loading = true
            try {
                shoppingListService.addShoppingList(element, userId)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    fun deleteShoppingList(userId: String?, element: String?) {
        viewModelScope.launch {
            loading = true
            try {
                shoppingListService.deleteShoppingList(userId, element)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}
