package com.example.se3_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.se3_app.startView.StartView
import com.example.se3_app.startView.StartViewModel


import com.example.se3_app.ui.theme.SE3AppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.se3_app.cocktailSearchView.CocktailSearchView
import com.example.se3_app.cocktailSearchView.CocktailSearchViewModel
import com.example.se3_app.einkaufslitenView.EinkaufslistenView
import com.example.se3_app.einkaufslitenView.EinkaufslistenViewModel
import com.example.se3_app.hinzufuegenView.HinzufuegenView
import com.example.se3_app.hinzufuegenView.HinzufuegenViewModel
import com.example.se3_app.merklistenView.MerklistenView
import com.example.se3_app.merklistenView.MerklistenViewModel
import com.example.se3_app.resultView.ResultView
import com.example.se3_app.resultView.ResultViewModel
import com.example.se3_app.rezeptView.RezeptView
import com.example.se3_app.rezeptView.RezeptViewModel


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
    val cocktailSearchViewModel = CocktailSearchViewModel()
    val einkaufslistenViewModel = EinkaufslistenViewModel()
    val hinzufuegenViewModel = HinzufuegenViewModel()
    val merklistenViewModel = MerklistenViewModel()
    val resultViewModel = ResultViewModel()
    val rezeptViewModel = RezeptViewModel()
    val navController = rememberNavController()

    NavHost(navController, startDestination = "startView") {
        composable(route = "startView"){
            StartView(navController, startViewModel)
        }
        composable(route = "cocktailSearchView"){
            CocktailSearchView(navController, cocktailSearchViewModel)
        }
        composable(route = "einkaufslistenView"){
            EinkaufslistenView(navController, einkaufslistenViewModel)
        }
        composable(route = "hinzufuegenView"){
            HinzufuegenView(navController, hinzufuegenViewModel)
        }
        composable(route = "merklistenView"){
            MerklistenView(navController, merklistenViewModel)
        }
        composable(route = "resultView"){
            ResultView(navController, resultViewModel)
        }
        composable(route = "rezeptView"){
            RezeptView(navController, rezeptViewModel)
        }
    }
}