package com.example.se3_app.firebase.verify_email

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VerifyEmailViewModel: ViewModel(){
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
}