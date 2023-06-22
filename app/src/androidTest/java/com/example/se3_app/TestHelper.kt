package com.example.se3_app

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.ComposeContentTestRule

@OptIn(ExperimentalMaterial3Api::class)
fun ComposeContentTestRule.launchapp_se3App(context: Context) {
    setContent {
        CocktailApp()
    }
}
