package com.example.se3app.startView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3app.Dto.CocktailDto
import com.example.se3app.service.CocktailService
import kotlinx.coroutines.launch

class StartViewModel: ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    private val cocktailService = CocktailService()
    var cocktails: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    lateinit var incredients: List<String>

    fun getAllCocktails() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allCocktails = cocktailService.findCocktails()
                cocktails = allCocktails.toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }

    fun getAllIncredients(){
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allIncredients = cocktailService.getIngredients()
                incredients = allIncredients.toMutableList()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }
}