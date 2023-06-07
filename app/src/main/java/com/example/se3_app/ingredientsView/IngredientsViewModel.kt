package com.example.se3_app.ingredientsView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.service.CocktailService
import kotlinx.coroutines.launch

class IngredientsViewModel: ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    private val cocktailService = CocktailService()
    var cocktails: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    lateinit var ingredients: List<String>

    fun getAllIncredients(){
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allIncredients = cocktailService.getIngredients()
                ingredients = allIncredients
                loading = false
                println("Zutaten $ingredients")
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }
}