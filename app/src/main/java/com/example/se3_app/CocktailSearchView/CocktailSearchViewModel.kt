package com.example.se3_app.CocktailSearchView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CocktailSearchViewModel {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
}