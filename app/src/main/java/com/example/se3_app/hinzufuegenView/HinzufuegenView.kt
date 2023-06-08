package com.example.se3_app.hinzufuegenView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.AddCocktailDto
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.cocktailSearchView.font
import com.example.se3_app.cocktailSearchView.options
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.ui.theme.chipFarbe1
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe6


val newCocktail = AddCocktailDto("", emptyArray(),"", true, "", "")
@Composable
fun HinzufuegenView(navController: NavController, viewModel: MainViewModel) {

    if (viewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        options = viewModel.tastes
        HinzufuegenViewContent(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HinzufuegenViewContent(navController: NavController, viewModel: MainViewModel) {
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
        TopAppBar(modifier = Modifier.fillMaxWidth(), navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.logo_app_icon),
                contentDescription = "Menu",
                modifier = Modifier.size(40.dp)
            )
        }, title = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "MIX'N'FIX",
                    fontFamily = com.example.se3_app.startView.font,
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }, actions = {
            IconButton(onClick = { navController.navigate("HelpView")


            }) {
                Icon(Icons.Filled.Info, contentDescription = "Search Icon")
            }
        })

        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Eigenschaften deines neuen Cocktails:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            //Eingabefeld des Nutzers
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text(text = "Der Name deines Cocktails") },
                placeholder = { Text(text = "Cocktainame") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = chipFarbe2,
                    cursorColor = Color.Black, // Farbe des Cursors
                    focusedIndicatorColor = chipFarbe6, // Farbe des Fokusindikators
                    unfocusedIndicatorColor = Color.Gray // Farbe des nicht fokussierten Indikators
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
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
                            .height(80.dp),
                    ) {
                        Text(
                            text = "Enthält der Cocktail Alkohol?",
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val minValue = 0
                            val maxValue = 1
                            val values = listOf("ja", "nein")

                            val selectedValue = remember { mutableStateOf(minValue) }
                            Slider(
                                value = selectedValue.value.toFloat(),
                                colors = SliderDefaults.colors(
                                    thumbColor = chipFarbe6,
                                    activeTrackColor = chipFarbe1
                                ),
                                onValueChange = { newValue ->
                                    selectedValue.value = newValue.toInt()
                                },
                                valueRange = minValue.toFloat()..maxValue.toFloat(),
                                steps = maxValue - minValue
                            )

                            val text = values[selectedValue.value]
                            Text(
                                text = text,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Auswahl der Zutaten

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
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
                    ) {
                        Text(
                            text = "Welche Zutaten enthält der Cocktail?",
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(), contentAlignment = Alignment.Center
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(all = 4.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    FloatingActionButton(
                                        onClick = {
                                            viewModel.getAllIncredients()
                                            navController.navigate("ingredientsView")
                                        },
                                        modifier = Modifier
                                            .height(40.dp)
                                            .fillMaxWidth(),
                                        containerColor = chipFarbe6
                                    ) {
                                        Text("Zutatenfilter", fontFamily = font)
                                    }
                                }

                            }
                        }
                    }
                }
            }

            // Auswahl der Schwierigkeit
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
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
                            .height(80.dp),
                    ) {
                        Text(
                            text = "Welchen Schwierigkeitsgrad hat der Cocktail?",
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val minValue = 0
                            val maxValue = 2
                            val values = listOf("simpel", "mittel", "schwer")

                            val selectedValue = remember { mutableStateOf(minValue) }
                            Slider(
                                value = selectedValue.value.toFloat(),
                                colors = SliderDefaults.colors(
                                    thumbColor = chipFarbe6,
                                    activeTrackColor = chipFarbe1
                                ),
                                onValueChange = { newValue ->
                                    selectedValue.value = newValue.toInt()
                                },
                                valueRange = minValue.toFloat()..maxValue.toFloat(),
                                steps = maxValue - minValue
                            )

                            val text = values[selectedValue.value]
                            Text(
                                text = text,
                            )
                        }
                    }
                }
            }

            // Die Geschmacksrichtung auswählen
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
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
                            .height(80.dp),
                    ) {
                        Text(
                            text = "Welchem Geschmack ist er zuzuordnen?",
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp),
                    ) {

                        var isExpaned by remember {
                            mutableStateOf(false)
                        }

                        var geschmack by remember {
                            mutableStateOf("")
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                modifier = Modifier.background(chipFarbe2),
                                onExpandedChange = {
                                    expanded = !expanded
                                }
                            ) {
                                TextField(
                                    modifier = Modifier.menuAnchor(),
                                    readOnly = true,
                                    value = "egal",
                                    onValueChange = { },
                                    label = { Text("Geschmack") },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expanded
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    modifier = Modifier.background(chipFarbe2),
                                    onDismissRequest = {
                                        expanded = false
                                    }
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(text = selectionOption) },
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                            }
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Eingabefeld des Nutzers
            var beschreibungsText by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth().height(300.dp),
                value = beschreibungsText,
                onValueChange = {
                    beschreibungsText = it
                },
                label = { Text(text = "Beschreibe deinen Cocktail") },
                placeholder = {
                    Text(
                        "Nutze für deine Beschreibung am Besten einzelne Schritte. Gebe außerdem die Mengenanzahl deiner Zutaten an. Beispiel:\nSchritt 1: Die beiden Limettenenden der unbehandelten Limetten abschneiden, die Frucht achteln und in ein Tumbler-Glas geben. \nSchritt 2: Danach 2EL braunen Zucker drüber verteilen und die Limettenstücke mit einem Stößel leicht ausdrücken. \nSchritt 3: 6cl Cachaca dazugeben und das Glas mit 5EL Crushed Ice auffüllen - alles gut durchrühren.\nSchritt 4: Eventuell noch einen Schuss Soadwasser hinzufügen und den Cocktail mit einem Trinkhalm servieren. ",
                        fontSize = 10.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = chipFarbe2,
                    cursorColor = Color.Black, // Farbe des Cursors
                    focusedIndicatorColor = chipFarbe6, // Farbe des Fokusindikators
                    unfocusedIndicatorColor = Color.Gray // Farbe des nicht fokussierten Indikators
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Der Suche Button
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("RezeptView") }, //andere Seite einfügen
                    modifier = Modifier
                        .height(40.dp).fillMaxWidth(),
                    containerColor = chipFarbe6
                ) {
                    Text("Hinzufügen")
                }
            }
            Spacer(modifier = Modifier.height(200.dp))

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
                    selected = selectedItem == 1,
                    onClick = {
                        if (index == 1) {
                            viewModel.getAllTastes()
                        }
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }

}