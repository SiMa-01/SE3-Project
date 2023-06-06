package com.example.se3app.firebase.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignInViewModel: ViewModel(){
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var veriviziert: Boolean by mutableStateOf(false)
    var anmeldungOk: Boolean by mutableStateOf(false)
    var userId: String? = null // UserID des angemeldeten Benutzers


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        // Anmeldung erfolgreich und E-Mail-Adresse verifiziert
                        userId = user.uid
                        veriviziert = true
                        anmeldungOk = true

                        user.getIdToken(true)
                            .addOnCompleteListener { tokenTask ->
                                if (tokenTask.isSuccessful) {
                                    val idToken = tokenTask.result?.token
                                    // Hier hast du den JWT (idToken) des angemeldeten Benutzers
                                    // Führe hier weitere Operationen mit dem JWT durch
                                    println("idToken:$idToken")
                                } else {
                                    // Fehler beim Abrufen des JWT
                                    val exception = tokenTask.exception
                                    // Behandle den Fehler entsprechend
                                }
                            }

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