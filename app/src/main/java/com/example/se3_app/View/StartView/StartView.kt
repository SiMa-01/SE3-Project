package com.example.se3_app.View.StartView

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun StartView(navController: NavController, viewModel: StartViewModel) {
    StartViewContent(navController)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartViewContent(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Person,
        Icons.Filled.Favorite, Icons.Filled.List)
    //Icons.Filled.Settings

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(title = { Text("ThemeDemo") }, scrollBehavior = null)

        Button(onClick = { }) {
            Text("MD3 Button")
        }

        Text("A Theme Demo")

        FloatingActionButton(onClick = { }) {
            Text("FAB")
        }

        BottomAppBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(icons[index], contentDescription = null) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }







}

@Composable
fun TextInBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text
        )
    }
}




