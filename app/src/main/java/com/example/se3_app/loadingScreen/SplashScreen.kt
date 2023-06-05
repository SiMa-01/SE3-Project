package com.example.se3_app.loadingScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.se3_app.R
import com.example.se3_app.startView.StartViewModel
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavController, startViewModel: StartViewModel) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        startViewModel.getAllCocktails()


        while (startViewModel.loading) {
            delay(500) // Wartezeit vor der erneuten Überprüfung
        }

        navController.popBackStack()
        navController.navigate("startView")
    }
    Splash(alpha = alphaAnim.value, startViewModel)
}

@Composable
fun Splash(alpha: Float, startViewModel: StartViewModel) {
    Box(
        modifier = Modifier
            .background(Color(0xFFAAD6E3))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cocktail),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha = alpha)
                    .align(Alignment.CenterHorizontally)
            )



            if (startViewModel.loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    trackColor = Color.Blue,
                    color = Color.Black
                )
            }
        }
    }
}
