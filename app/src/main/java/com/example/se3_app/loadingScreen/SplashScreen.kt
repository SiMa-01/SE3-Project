package com.example.se3_app.loadingScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        viewModel.getAllCocktails()
        delay(3000)
        listViewModel.getFavouriteList(listViewModel.userId)
        delay(3000)
        listViewModel.getShoppingList(listViewModel.userId)
        delay(3000)
        viewModel.getCocktailByName("MIX'N'FIX")
        while (viewModel.loading || listViewModel.loading) {
            delay(1000)
        }
        navController.popBackStack()
        navController.navigate("startView")
    }
    Splash(alpha = alphaAnim.value, viewModel)
}

@Composable
fun Splash(alpha: Float, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize().testTag("splashView")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha = alpha)
                    .align(Alignment.CenterHorizontally)
            )

            if (viewModel.loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    color = Color.Blue
                )
            }
        }
    }
}
