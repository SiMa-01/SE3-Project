package com.example.se3_app.View.StartView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.se3_app.R


@Composable
fun StartView(navController: NavController, viewModel: StartViewModel) {
    StartViewContent(navController, viewModel)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartViewContent(navController: NavController, viewModel: StartViewModel) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Person,
        Icons.Filled.Favorite, Icons.Filled.List
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /*Image(
                        painter = painterResource(id = R.drawable.cocktail), // Das Logo wird aktuell noch viel zu groß angezeigt. Daher habe ich es noch nicht hingekommen
                        contentDescription = "Logo",
                        modifier = Modifier.padding(end = 8.dp)
                    )*/
                    Text(
                        text = "MIX'N'FIX",
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = { /* Navigationsaktion ausführen */ }
                ) {
                    // Hier können Sie ein Navigations-Icon hinzufügen, z.B. ein Menü-Icon
                }
            },
        )


//Andorid studio Jetpack compose


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Der Cocktail der Woche")
            FloatingActionButton(
                onClick = { }, //andere Seite einfügen
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Text("Gin Tonic") // TODO: Hier kommt ein random Cocktail hin
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    )
            {
                FloatingActionButton(
                    onClick = { }, //andere Seite einfügen
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                ) {
                    Text("Cocktail suchen")
                }
                FloatingActionButton(
                    onClick = { }, //andere Seite einfügen
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp)
                ) {
                    Text("Rezept hinzufügen")
                }
            }
            Text("Zuletzt gesucht")
/*
            // TODO oder wieder raus
            //val data by datenbankenViewModel.aktivitaet.observeAsState(initial = emptyList())
            val data: Int = 1
            LazyColumn {
                items (data) { item ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)) {
                        FloatingActionButton(
                            onClick = { }, //andere Seite einfügen
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 5.dp)
                                .height(80.dp)
                        ) {
                            Text("Gin Tonic") // TODO: Hier kommt ein random Cocktail hin
                        }
                    }

                }
            }*/


            Button(onClick = { }) {
                Text("MD3 Button")
            }
            // Hier ist der Test der Methode
            val name = "Gin Tonic"
            val ingredients = arrayOf("Wodka 1", "Tonic", "Eis")
            val difficulty = "EASY"
            var alcoholic by remember { mutableStateOf(true) }
            val taste = "SWEET"

            Cocktailbox(
                navController,
                viewModel,
                name,
                ingredients,
                difficulty,
                alcoholic,
                taste
            )
        }


    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomCenter)
    ) {
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

@Composable
fun Cocktailbox(navController: NavController, startViewModel: StartViewModel, name: String, ingredients: Array<String>, difficulty: String, alcoholic: Boolean, taste: String) {
    val customColor = Color(0xFFFF9800)
    Text ("Hallo hier bin ich")

    if (startViewModel.errorMessage.isEmpty()) {
        FloatingActionButton(
            onClick = { }, //andere Seite einfügen
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            {
                Box (
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                )
                {
                    Text(name)
                }
                Box (
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                {
                    if (difficulty == "EASY"){
                        Box (
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Green)
                        )
                    }
                    else if (difficulty == "MEDIUM"){
                        Box (
                            modifier = Modifier
                                .size(8.dp)
                                .background(customColor)
                        )
                    }
                    else if (difficulty == "HARD") {
                        Box (
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Red)
                        )
                    }
                    if (alcoholic){
                        Text ("%")
                    }
                    else {
                        Text ("kein %")
                    }
                    for (item in ingredients) {
                        Text(text = item)
                    }
                    Text(taste)

                }
            }
        }
        }
    /*if (startViewModel.errorMessage.isEmpty()) {
        FloatingActionButton(
            onClick = { }, //andere Seite einfügen
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text("Gin Tonic") // TODO: Hier kommt ein random Cocktail hin
        }
        LazyColumn {
            itemsIndexed(items = startViewModel.users) { index, item ->
                Box(modifier = Modifier.clickable {
                    navController.navigate("userDetailView/$index")
                }) {
                    UserCell(userDto = item, userImage = startViewModel.userImage)
                }
            }
        }
    } else {
        Text(text = startViewModel.errorMessage, color = Color.Red,
            fontWeight = FontWeight.Bold)
    }*/
}