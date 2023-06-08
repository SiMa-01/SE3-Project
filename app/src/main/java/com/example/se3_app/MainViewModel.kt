package com.example.se3_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.Dto.AddCocktailDto
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.service.CocktailService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    private val cocktailService = CocktailService()
    var cocktailByName: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    var cocktailsAll: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    lateinit var tastes: List<String>
    lateinit var addedCocktail: CocktailDto
    var cocktailsSearch: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    var selectedIngredients:  MutableList<String> = emptyList<String>().toMutableList()
    lateinit var ingredients: List<String>


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

    fun getAllTastes(){
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                tastes = cocktailService.getTastes()
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
                println("fehler $errorMessage")
            }
        }
    }

    fun searchCocktails(name:String, taste:String, incredience: List<String>, stringAlcoholic:String, difficuty:String){
        var alcoholic: Boolean? = null;
        if (stringAlcoholic == "ja"){
            var alcoholic: Boolean = true;
        }
        else if (stringAlcoholic == "nein"){
            var alcoholic: Boolean = false;
        }
        viewModelScope.launch {
            errorMessage = ""
            loading = true
            try {
                val searchCocktails = cocktailService.findCocktails(name, taste, incredience, alcoholic, difficuty)
                cocktailsSearch = searchCocktails.toMutableList()
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

    fun addCocktail(addCocktailDto: AddCocktailDto) {
        viewModelScope.launch {
            loading = true
            try {
                addedCocktail = cocktailService.addCocktail(addCocktailDto)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}