package com.example.se3_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.service.CocktailService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    private val cocktailService = CocktailService()
    var cocktailByName: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    var cocktailsAll: MutableList<CocktailDto> by mutableStateOf(mutableListOf())


    fun getCocktailByName(name: String) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val cocktail = cocktailService.findCocktails(name)
                cocktailByName = cocktail.toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }

    fun getAllCocktails() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allCocktails = cocktailService.findCocktails()
                cocktailsAll = allCocktails.toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }
}