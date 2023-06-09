package com.example.se3app.merklistenView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MerklistenViewModel {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
}