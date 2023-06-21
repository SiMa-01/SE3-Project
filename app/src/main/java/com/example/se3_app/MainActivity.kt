package com.example.se3_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.se3_app.favoriteListView.FavoriteListView
import com.example.se3_app.firebase.forgot_password.ForgotPasswordView
import com.example.se3_app.firebase.forgot_password.ForgotPasswordViewModel
import com.example.se3_app.firebase.sign_in.SignInView
import com.example.se3_app.firebase.sign_in.SignInViewModel
import com.example.se3_app.firebase.sign_up.SignUpView
import com.example.se3_app.firebase.sign_up.SignUpViewModel
import com.example.se3_app.firebase.verify_email.VerifyEmailView
import com.example.se3_app.helpView.HelpView
import com.example.se3_app.ingredientsView.IngredientsView
import com.example.se3_app.itemListView.ItemListView
import com.example.se3_app.loadingScreen.AnimatedSplashScreen
import com.example.se3_app.newCocktailView.NewCocktailView
import com.example.se3_app.picturesView.PicturesView
import com.example.se3_app.recipeCocktailView.RecipeCocktailView
import com.example.se3_app.resultView.ResultView
import com.example.se3_app.searchCocktailView.SearchCocktailView
import com.example.se3_app.startView.StartView
import com.example.se3_app.ui.theme.SE3AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SE3AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CocktailApp()
                }
            }
        }
    }
}
    @Composable
    fun CocktailApp() {
        val mainViewModel = MainViewModel()
        val listViewModel = ListViewModel()

        val signInViewModel = SignInViewModel()
        val signUpViewModel = SignUpViewModel()
        val forgotPasswordViewModel = ForgotPasswordViewModel()

        val navController = rememberNavController()

        NavHost(navController, startDestination = "signInView") {
            composable(route = "startView") {
                StartView(navController, mainViewModel, listViewModel)
            }
            composable(route = "searchCocktailView") {
                SearchCocktailView(navController, mainViewModel, listViewModel)
            }
            composable(route = "itemListView") {
                ItemListView(navController, mainViewModel, listViewModel)
                // SignInView(navController, signInViewModel)
            }
            composable(route = "newCocktailnView") {
                NewCocktailView(navController, mainViewModel, listViewModel)
            }
            composable(route = "favoriteListView") {
                FavoriteListView(navController, mainViewModel, listViewModel)
            }
            composable(route = "resultView") {
                ResultView(navController, mainViewModel, listViewModel)
            }
            composable(route = "rezeptView") {
                RecipeCocktailView(navController, mainViewModel, listViewModel)
            }
            composable(route = "signInView") {
                SignInView(navController, signInViewModel, listViewModel)
            }
            composable(route = "signUpView") {
                SignUpView(navController, signUpViewModel)
            }
            composable(route = "forgotPasswordView") {
                ForgotPasswordView(navController, forgotPasswordViewModel)
            }
            composable(route = "verifyEmailView") {
                VerifyEmailView(navController)
            }
            composable(route = "splashScreen") {
                AnimatedSplashScreen(navController, mainViewModel, listViewModel)
            }
            composable(route = "ingredientsView") {
                IngredientsView(navController, mainViewModel)
            }
            composable(route = "helpView") {
                HelpView(navController, mainViewModel, listViewModel, signInViewModel)
            }
            composable(route = "picturesView") {
                PicturesView(navController, mainViewModel)
            }
        }
    }
