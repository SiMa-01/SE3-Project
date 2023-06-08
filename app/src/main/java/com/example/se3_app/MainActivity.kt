package com.example.se3_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.se3_app.ui.theme.SE3AppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.se3_app.cocktailSearchView.CocktailSearchView
import com.example.se3_app.einkaufslitenView.EinkaufslistenView
import com.example.se3_app.firebase.forgot_password.ForgotPasswordView
import com.example.se3_app.firebase.forgot_password.ForgotPasswordViewModel
import com.example.se3_app.firebase.sign_in.SignInView
import com.example.se3_app.firebase.sign_in.SignInViewModel
import com.example.se3_app.firebase.sign_up.SignUpView
import com.example.se3_app.firebase.sign_up.SignUpViewModel
import com.example.se3_app.firebase.verify_email.VerifyEmailView
import com.example.se3_app.firebase.verify_email.VerifyEmailViewModel
import com.example.se3_app.hinzufuegenView.HinzufuegenView
import com.example.se3_app.ingredientsView.IngredientsView
import com.example.se3_app.ingredientsView.IngredientsViewModel
import com.example.se3_app.loadingScreen.AnimatedSplashScreen
import com.example.se3_app.merklistenView.MerklistenView
import com.example.se3_app.resultView.ResultView
import com.example.se3_app.rezeptView.RezeptView
import com.example.se3_app.startView.StartView


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

        val mainViewModel = MainViewModel()

        val signInViewModel = SignInViewModel()
        val signUpViewModel = SignUpViewModel()
        val forgotPasswordViewModel = ForgotPasswordViewModel()
        val verifyEmailViewModel = VerifyEmailViewModel()
        val ingredientsViewModel = IngredientsViewModel()
        val navController = rememberNavController()

        NavHost(navController, startDestination = "splashScreen") {
            composable(route = "startView") {
                StartView(navController, mainViewModel)
            }
            composable(route = "cocktailSearchView") {
                CocktailSearchView(navController, mainViewModel, ingredientsViewModel)
            }
            composable(route = "einkaufslistenView") {
                EinkaufslistenView(navController, mainViewModel)
                //SignInView(navController, signInViewModel)
            }
            composable(route = "hinzufuegenView") {
                HinzufuegenView(navController, mainViewModel)
            }
            composable(route = "merklistenView") {
                MerklistenView(navController, mainViewModel)
            }
            composable(route = "resultView") {
                ResultView(navController, mainViewModel)
            }
            composable(route = "rezeptView") {
                RezeptView(navController, mainViewModel)
            }
            composable(route = "signInView") {
                SignInView(navController, signInViewModel)
            }
            composable(route = "signUpView") {
                SignUpView(navController, signUpViewModel)
            }
            composable(route = "forgotPasswordView") {
                ForgotPasswordView(navController, forgotPasswordViewModel)
            }
            composable(route = "verifyEmailView") {
                VerifyEmailView(navController, verifyEmailViewModel)
            }
            composable(route = "splashScreen") {
                AnimatedSplashScreen(navController, mainViewModel)
            }
            composable(route = "ingredientsView") {
                IngredientsView(navController, ingredientsViewModel)
            }
        }
    }
}