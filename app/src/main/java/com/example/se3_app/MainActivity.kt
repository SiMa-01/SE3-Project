package com.example.se3_app

import android.os.Bundle
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
}
@Composable
fun CocktailApp(){
    val startViewModel = StartViewModel()
    val cocktailViewModel = CocktailSearchViewModel()
    val navController = rememberNavController()

    NavHost(navController, startDestination = "startView") {
        composable(route = "startView"){
            StartView(navController, startViewModel)
        }
        composable(route = "cocktailSearchView"){
            CocktailSearchView(navController, cocktailViewModel)
        }
    }
}