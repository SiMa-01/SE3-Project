package com.example.se3_app.startView

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.service.CocktailService
import kotlinx.coroutines.launch

class StartViewModel: ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    private val cocktailService = CocktailService()
    var cocktails: MutableList<CocktailDto> by mutableStateOf(mutableListOf())

    fun getAllCocktails() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allUsers = cocktailService.findCocktails(null, null, null, null, null)
                loading = false
                cocktails = allUsers.toMutableList()

            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}