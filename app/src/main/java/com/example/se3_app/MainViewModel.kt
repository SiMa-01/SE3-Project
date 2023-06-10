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
    lateinit var tastes: List<String>
    lateinit var addedCocktail: CocktailDto
    var cocktailsSearch: MutableList<CocktailDto> by mutableStateOf(mutableListOf())
    var selectedIngredients:  MutableList<String> = emptyList<String>().toMutableList()
    lateinit var ingredients: List<String>

    var cameFrom: Int = 0
    var comeBack: Array<Any> = arrayOf("", 0f, 0, "egal", "")
    var comeBack2: Array<Any> = arrayOf("", false, 0, "bitter", "")

    var filterListe: MutableList<String> = emptyList<String>().toMutableList()

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

    fun searchCocktails(name: String? = null,  stringTaste: String? = null, ingredients: List<String> = emptyList(), stringAlcoholic: String? = null, stringDifficulty:String? = null){
        println("Hallo ich bin hier")
        viewModelScope.launch {
            println("In der Methode")
            var alcoholic: Boolean? = null
            if (stringAlcoholic == "ja"){
                alcoholic = true
            }
            else if (stringAlcoholic == "nein"){
                alcoholic = false
            }
            var difficulty: String? = stringDifficulty
            if (stringDifficulty == "egal") {
                 difficulty = null
            }
            var taste: String? = stringTaste
            if (stringTaste == "egal") {
                taste = null
            }
            errorMessage = ""
            loading = true
            try {
                println("Im try " + taste + ingredients + alcoholic + difficulty)
                val searchCocktails = cocktailService.findCocktails(name, taste, ingredients, alcoholic, difficulty)
                println("Das Ergebnis " + searchCocktails[0].name)
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

    fun addCocktail(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            loading = true
            try {
                addedCocktail = cocktailService.addCocktail(cocktailDto)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}