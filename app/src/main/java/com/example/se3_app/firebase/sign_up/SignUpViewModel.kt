package com.example.se3_app.firebase.sign_up

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpViewModel: ViewModel(){
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var emailOk: Boolean by mutableStateOf(false)
    var registrierung: Boolean by mutableStateOf(true)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registrierung erfolgreich
                    val user: FirebaseUser? = auth.currentUser

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                // E-Mail-Bestätigung erfolgreich gesendet
                                emailOk = true
                            } else {
                                // E-Mail-Bestätigung fehlgeschlagen
                                val exception: Exception? = verificationTask.exception
                                emailOk = false
                            }
                        }
                } else {
                    // Registrierung fehlgeschlagen
                    val exception: Exception? = task.exception
                    registrierung = false
                }
            }
    }
}