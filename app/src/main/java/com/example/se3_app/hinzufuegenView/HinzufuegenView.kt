package com.example.se3_app.hinzufuegenView

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.startView.navigateToDestination

@Composable
fun HinzufuegenView(navController: NavController, viewModel: MainViewModel) {
    HinzufuegenViewContent(navController, viewModel)

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
                            text = "Enthält der Cocktail Alkohol??",
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
                            .weight(1f)

                    ) {
                        Column {
                            val zutaten = arrayOf("apple", "banana", "cherry")// TODO Marcel: Hier nehmen wir dann das Array der Zutaten zurück
                            var counter = 0
                            for (x in zutaten){
                                val checkedState = remember { mutableStateOf(false) }
                                Row{
                                    Checkbox(
                                        checked = checkedState.value,
                                        onCheckedChange = { checkedState.value = it },
                                    )
                                    Text(text = zutaten[counter], fontSize = 15.sp, modifier = Modifier.padding(vertical = 14.dp))
                                    counter = counter + 1
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
                                expanded = isExpaned,
                                onExpandedChange = { isExpaned = it }) {
                                TextField(
                                    value = geschmack,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpaned)
                                    },
                                    modifier = Modifier.menuAnchor()
                                )
                                ExposedDropdownMenu(
                                    expanded = isExpaned,
                                    onDismissRequest = { isExpaned = false }) {
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = "Sweet")
                                        },
                                        onClick = {
                                            geschmack = "Sweet"
                                            isExpaned = false
                                        })
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = "Sour")
                                        },
                                        onClick = {
                                            geschmack = "Sour"
                                            isExpaned = false
                                        })
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = "Bitter")
                                        },
                                        onClick = {
                                            geschmack = "Bitter"
                                            isExpaned = false
                                        })
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
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }

}