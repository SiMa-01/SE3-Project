package com.example.se3_app.startView

import androidx.compose.foundation.background
import kotlinx.coroutines.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp

@Composable
fun StartView(navController: NavController, viewModel: StartViewModel) {
    viewModel.getAllCocktails()
    StartViewContent(navController, viewModel)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartViewContent(navController: NavController, viewModel: StartViewModel) {
    // Das müssen wir vermutlich überall hin kopieren


    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Search,
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
                    Text("MIX'N'FIX", fontSize = 30.sp)
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

        //--------------------------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Der Cocktail der Woche", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            println("drink ${viewModel.cocktails}")
            val name = "Gin Tonic"
            var ingredients = arrayOf("Wodka", "Tonic", "Eis")
            val difficulty = "EASY"
            var alcoholic by remember { mutableStateOf(true) }
            val taste = "Sweet"

            StartCocktailbox(
                navController,
                viewModel,
                name,
                ingredients,
                difficulty,
                alcoholic,
                taste
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Funktionen", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    )
            {
                FloatingActionButton(
                    onClick = {navController.navigate("CocktailSearchView")}, //andere Seite einfügen
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                ) {
                    Text("Cocktail suchen")
                }
                FloatingActionButton(
                    onClick = {navController.navigate("HinzufuegenView")}, //andere Seite einfügen
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp)
                ) {
                    Text("Rezept hinzufügen")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Zum inspirieren", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Hier ist der Test der Methode
            ingredients = arrayOf("Cachaca",  "Limette", "Rohrzucker")
            StartCocktailbox(navController, viewModel, "Caipirinha", ingredients, "MEDIUM", alcoholic, "Sour")
            ingredients = arrayOf("Rum", "Soda", "Limette", "Rohrzucker")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")
            StartCocktailbox(navController, viewModel, "Mojito", ingredients, "EASY", alcoholic, "Sour")

            //Damit die NavigationBar drüber passt
            Spacer(modifier = Modifier.height(100.dp))

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
                    icon = { Icon(icons[index], contentDescription = "Home") },
                    label = { Text(item) },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }

    }
}

// Das ist die Methode nur für diese View
@Composable
fun StartCocktailbox(navController: NavController, startViewModel: StartViewModel, name: String, ingredients: Array<String>, difficulty: String, alcoholic: Boolean, taste: String) {
    if (startViewModel.errorMessage.isEmpty()) {
        Cocktailbox(navController, name, ingredients, difficulty, alcoholic, taste)
    }
}


// Das ist die allgemeine Methode auf die alle gehen


@Composable
fun Cocktailbox(navController: NavController, name: String, ingredients: Array<String>, difficulty: String, alcoholic: Boolean, taste: String) {
    val customColor = Color(0xFFFF9800)
        FloatingActionButton(
            onClick = {navController.navigate("RezeptView") },
            modifier = Modifier.fillMaxWidth()
                .height(80.dp)
        ) {
            // Das ist der linke Teil des Buttons
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
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(text = name,fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                            )
                            {
                                //Den Schwierigkeitsgrad durch Farben darstellen
                                if (difficulty == "EASY") {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(Color.Green)
                                    )
                                } else if (difficulty == "MEDIUM") {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(customColor)
                                    )
                                } else if (difficulty == "HARD") {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(Color.Red)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                            )
                            {
                                //Den Alkoholgehalt durch Alkohol darstellen
                                if (alcoholic) {
                                    Text("%")
                                } else {
                                    Text("kein %")
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)

                            ) {      //wenn der Button geklickt wird -> zur Merkliste hinzufügen
                                val isClicked = remember { mutableStateOf(false) }
                                IconButton(
                                    onClick = {
                                        isClicked.value = !isClicked.value }, //hier muss der Post rein
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        Icons.Filled.Star,
                                        contentDescription = "Zur Merkliste",
                                        tint = if (isClicked.value) Color.Yellow else Color.Unspecified
                                    )
                                    if (isClicked.value)
                                    {}                              //Post methoden in Merkliste
                                    else
                                    {}                                 // da passiert nix
                                }
                            }
                        }
                    }
                }
                Box (
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp),
                )
                {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        //Den Zutaten auflisten
                        var Zutaten = ""
                        for (item in ingredients) {
                            Zutaten = Zutaten + ", " +  item
                        }
                        Zutaten = Zutaten.drop(2)
                        Text(Zutaten)

                        //Den Geschmack nennen
                        Text(taste)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
}
    fun navigateToDestination(navController: NavController, index: Int) {
       when (index) {
            0 -> navController.navigate("StartView")
            1 -> navController.navigate("CocktailSearchView")
            2 -> navController.navigate("MerklistenView")
            3 -> navController.navigate("EinkaufslistenView")
        }
}


