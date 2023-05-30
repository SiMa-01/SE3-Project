package com.example.se3_app.firebase.forgot_password

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel {
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    var emailRegistriert: Boolean by mutableStateOf(false)
    var emailReset: Boolean by mutableStateOf(false)


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun resetPassword(email: String) {

        val auth = FirebaseAuth.getInstance()

        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods ?: emptyList()

                    if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                        // E-Mail-Adresse registriert, Passwortzurücksetzungsmail senden
                        emailRegistriert = true
                        auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { resetTask ->
                                if (resetTask.isSuccessful) {
                                    // Passwortzurücksetzungsmail erfolgreich gesendet
                                    Log.d(TAG, "Passwortzurücksetzungsmail erfolgreich gesendet.")
                                    emailReset = true
                                } else {
                                    // Passwortzurücksetzungsmail senden fehlgeschlagen
                                    Log.w(
                                        TAG,
                                        "Passwortzurücksetzungsmail senden fehlgeschlagen.",
                                        resetTask.exception
                                    )
                                    emailReset = false
                                }
                            }
                    } else {
                        // E-Mail-Adresse nicht registriert
                        emailRegistriert = false
                        Log.w(TAG, "E-Mail-Adresse ist nicht registriert.")
                    }
                } else {
                    // Fehler beim Überprüfen der Anmeldearten für die E-Mail-Adresse
                    Log.w(
                        TAG,
                        "Fehler beim Überprüfen der Anmeldearten für die E-Mail-Adresse.",
                        task.exception
                    )
                }
            }
    }
}