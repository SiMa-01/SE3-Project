package com.example.se3_app.firebase.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)


    var veriviziert: Boolean by mutableStateOf(false)
    var anmeldungOk: Boolean by mutableStateOf(false)
    var userId: String? = null // UserID of the logged in user

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        // Registration successful and email address verified
                        userId = user.uid
                        veriviziert = true
                        anmeldungOk = true

                        user.getIdToken(true)
                            .addOnCompleteListener { tokenTask ->
                                if (tokenTask.isSuccessful) {
                                    val idToken = tokenTask.result?.token
                                    // Here you have the JWT (idToken) of the logged in user
                                    // Perform more operations with the JWT here
                                    println("idToken:$idToken")
                                } else {
                                    // Error when retrieving the JWT
                                    val exception = tokenTask.exception
                                    // Handle the error accordingly
                                }
                            }
                    } else {
                        // Registration successful, but email address not verified
                        veriviziert = false
                        anmeldungOk = true
                    }
                } else {
                    // Login failed
                    val exception: Exception? = task.exception
                    print(exception)
                    anmeldungOk = false
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
    }

}
