package com.example.se3_app.firebase.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var veriviziert: Boolean by mutableStateOf(false)
    var anmeldungOk: Boolean by mutableStateOf(false)
    var userId: String? = null // UserID of the logged in user

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            FirebaseAuth.getInstance().signOut()
            FirebaseAuth.getInstance().useAppLanguage()

            val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val currentUser = firebaseAuth.currentUser
                if (currentUser == null) {
                    // Der Benutzer wurde erfolgreich abgemeldet
                    println("Nutzer abgemeldet")
                    // Hier können Sie die Anmeldung fortsetzen
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user: FirebaseUser? = auth.currentUser
                                if (user != null && user.isEmailVerified) {
                                    // Registrierung erfolgreich und E-Mail-Adresse verifiziert
                                    userId = user.uid
                                    veriviziert = true
                                    anmeldungOk = true

                                    user.getIdToken(true)
                                        .addOnCompleteListener { tokenTask ->
                                            if (tokenTask.isSuccessful) {
                                                val idToken = tokenTask.result?.token
                                                // Hier haben Sie das JWT (idToken) des angemeldeten Benutzers
                                                // Führen Sie weitere Operationen mit dem JWT hier durch
                                                println("idToken:$idToken")
                                            } else {
                                                // Fehler beim Abrufen des JWT
                                                val exception = tokenTask.exception
                                                // Fehler entsprechend behandeln
                                            }
                                        }
                                } else {
                                    // Registrierung erfolgreich, aber E-Mail-Adresse nicht verifiziert
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

            // AuthStateListener hinzufügen, um den Abschluss der Abmeldung zu überwachen
            FirebaseAuth.getInstance().addAuthStateListener(authStateListener)
        }
    }

    fun logoutUser() {
        FirebaseAuth.getInstance().signOut()
    }
}
