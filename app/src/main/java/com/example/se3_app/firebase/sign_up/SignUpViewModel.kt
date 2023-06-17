package com.example.se3_app.firebase.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.se3_app.service.FavoriteListService
import com.example.se3_app.service.ShoppingListService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var exception: Exception? = null
    var loading: Boolean by mutableStateOf(false)
    var loading2: Boolean by mutableStateOf(true)

    private val favoriteListService = FavoriteListService()
    private val shoppingListService = ShoppingListService()

    var emailOk: Boolean by mutableStateOf(false)
    var registrierungOk: Boolean by mutableStateOf(false)

    var userId: String? = null // UserID of the logged in user

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null) {
                        userId = user.uid
                    }
                    addFavoriteUser(userId)
                    addShoppinglistUser(userId)

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                // Email confirmation sent successfully
                                emailOk = true
                            } else {
                                // Email confirmation failed
                                val exception: Exception? = verificationTask.exception
                                emailOk = false
                            }
                        }
                    registrierungOk = true
                    loading2 = false
                } else {
                    // Registration failed
                    exception = task.exception
                    loading2 = false
                    registrierungOk = false
                }
            }
    }

    private fun addFavoriteUser(userId: String?) {
        viewModelScope.launch {
            loading = true
            try {
                var addedUser = favoriteListService.addFavoriteUser(userId)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }

    private fun addShoppinglistUser(userId: String?) {
        viewModelScope.launch {
            loading = true
            try {
                shoppingListService.addShoppinglistUser(userId)
                loading = false
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}
