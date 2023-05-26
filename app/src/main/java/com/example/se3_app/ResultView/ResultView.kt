package com.example.se3_app.ResultView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MerklistenView.Cocktailbox
import com.example.se3_app.MerklistenView.MerklistenViewModel
import com.example.se3_app.View.StartView.StartViewContent
import com.example.se3_app.View.StartView.navigateToDestination

@Composable
fun ResultView(navController: NavController, viewModel: ResultViewModel) {
    ResultViewContent(navController, viewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultViewContent(navController: NavController, viewModel: ResultViewModel) {
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



    // Hier kommt der Inhalt der Seite hin
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Deine gewählten Filter: ", fontSize = 14.sp) // TODO: Marcel hier muss von der vorherigen Seite die Filiter hin

            Spacer(modifier = Modifier.height(8.dp))

            Text("Damit empfehlen wir dir diese Cocktails: ", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(8.dp))
            val name="Gin Tonic"
            var ingredients= arrayOf("Gin", "Tonic", "Eis")
            val difficulty="EASY"
            var alcoholic by remember { mutableStateOf(true)}
            val taste ="Sweet"

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

    Spacer(modifier = Modifier.height(100.dp))

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

}
@Composable
fun Cocktailbox(navController: NavController, resultViewModel: ResultViewModel, name: String, ingredients: Array<String>, difficulty: String, alcoholic: Boolean, taste: String) {
    val customColor = Color(0xFFFF9800)
    if (resultViewModel.errorMessage.isEmpty()) {
        FloatingActionButton(
            onClick = {navController.navigate("RezeptView") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            // Das ist der linke Teil des Buttons
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(text = name, fontSize = 15.sp)
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
                            // Das ist der rechte Teil des Buttons
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
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp),
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {


                        //Den Zutaten auflisten
                        var Zutaten = ""
                        for (item in ingredients) {
                            Zutaten = Zutaten + ", " + item
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
}




