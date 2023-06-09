package com.example.se3app.rezeptView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class RezeptViewModel {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
}