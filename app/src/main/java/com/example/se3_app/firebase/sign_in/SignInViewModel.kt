package com.example.se3_app.firebase.sign_in

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


class SignInViewModel: ViewModel(){
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var veriviziert: Boolean by mutableStateOf(false)
    var anmeldungOk: Boolean by mutableStateOf(false)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        // Anmeldung erfolgreich und E-Mail-Adresse verifiziert
                        veriviziert = true
                        anmeldungOk = true
                    } else {
                        // Anmeldung erfolgreich, aber E-Mail-Adresse nicht verifiziert
                        veriviziert = false
                        anmeldungOk = true
                    }
                } else {
                    // Anmeldung fehlgeschlagen
                    val exception: Exception? = task.exception
                    print(exception)
                    anmeldungOk = false
                }
            }
    }
}