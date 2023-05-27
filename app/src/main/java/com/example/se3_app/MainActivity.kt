package com.example.se3_app

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.se3_app.View.StartView.StartView
import com.example.se3_app.View.StartView.StartViewModel


import com.example.se3_app.ui.theme.SE3AppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.se3_app.CocktailSearchView.CocktailSearchView
import com.example.se3_app.CocktailSearchView.CocktailSearchViewModel
import com.example.se3_app.EinkaufslitenView.EinkaufslistenView
import com.example.se3_app.EinkaufslitenView.EinkaufslistenViewModel
import com.example.se3_app.HinzufuegenView.HinzufuegenView
import com.example.se3_app.HinzufuegenView.HinzufuegenViewModel
import com.example.se3_app.MerklistenView.MerklistenView
import com.example.se3_app.MerklistenView.MerklistenViewModel
import com.example.se3_app.ResultView.ResultView
import com.example.se3_app.ResultView.ResultViewModel
import com.example.se3_app.RezeptView.RezeptView
import com.example.se3_app.RezeptView.RezeptViewModel
import com.example.se3_app.firebase.EmailPasswordActivity
import com.example.se3_app.firebase.forgot_password.ForgotPasswordView
import com.example.se3_app.firebase.forgot_password.ForgotPasswordViewModel
import com.example.se3_app.firebase.sign_in.SignInView
import com.example.se3_app.firebase.sign_in.SignInViewModel
import com.example.se3_app.firebase.sign_up.SignUpView
import com.example.se3_app.firebase.sign_up.SignUpViewModel
import com.example.se3_app.firebase.verify_email.VerifyEmailView
import com.example.se3_app.firebase.verify_email.VerifyEmailViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GithubAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PlayGamesAuthProvider
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SE3AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CocktailApp()
                }
            }
        }
    }

    @Composable
    fun CocktailApp() {
        val startViewModel = StartViewModel()
        val cocktailSearchViewModel = CocktailSearchViewModel()
        val einkaufslistenViewModel = EinkaufslistenViewModel()
        val hinzufuegenViewModel = HinzufuegenViewModel()
        val merklistenViewModel = MerklistenViewModel()
        val resultViewModel = ResultViewModel()
        val rezeptViewModel = RezeptViewModel()
        val signInViewModel = SignInViewModel()
        val signUpViewModel = SignUpViewModel()
        val forgotPasswordViewModel = ForgotPasswordViewModel()
        val emailPasswordActivity = EmailPasswordActivity()
        val verifyEmailViewModel = VerifyEmailViewModel()
        val navController = rememberNavController()

        NavHost(navController, startDestination = "signInView") {
            composable(route = "startView") {
                StartView(navController, startViewModel)
            }
            composable(route = "cocktailSearchView") {
                CocktailSearchView(navController, cocktailSearchViewModel)
            }
            composable(route = "einkaufslistenView") {
                EinkaufslistenView(navController, einkaufslistenViewModel)
                //SignInView(navController, signInViewModel)
            }
            composable(route = "hinzufuegenView") {
                HinzufuegenView(navController, hinzufuegenViewModel)
            }
            composable(route = "merklistenView") {
                MerklistenView(navController, merklistenViewModel)
            }
            composable(route = "resultView") {
                ResultView(navController, resultViewModel)
            }
            composable(route = "rezeptView") {
                RezeptView(navController, rezeptViewModel)
            }
            composable(route = "signInView") {
                SignInView(navController, signInViewModel, emailPasswordActivity)
            }
            composable(route = "signUpView") {
                SignUpView(navController, signUpViewModel, emailPasswordActivity)
            }
            composable(route = "forgotPasswordView") {
                ForgotPasswordView(navController, forgotPasswordViewModel)
            }
            composable(route = "verifyEmailView") {
                VerifyEmailView(navController, verifyEmailViewModel)
            }
        }
    }
}