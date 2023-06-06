package com.example.se3_app.cocktailSearchView

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DropdownMenuItem
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
import com.example.se3_app.startView.navigateToDestination
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun CocktailSearchView(navController: NavController, viewModel: CocktailSearchViewModel) {
    CocktailSearchViewContent(navController, viewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailSearchViewContent(navController: NavController, viewModel: CocktailSearchViewModel) {

    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }


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
            Text("Wähle alle Einschränkungen für deinen Cocktail aus:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ){
                var textValue by remember { mutableStateOf(TextFieldValue()) }

                OutlinedTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    label = { Text("Name des Cocktails") },
                )
            }

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
                            text = "Soll der Cocktail Alkohol enthalten?",
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
                            val values = listOf("egal", "ja", "nein")

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
                            .height(80.dp),
                    ) {
                        Text(
                            text = "Welche Zutaten soll der Cocktail enthalten?",
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
                                /*
                                    Column {
                                        val zutaten = arrayOf("apple", "banana", "cherry", "apple", "banana", "cherry")// TODO Marcel: Hier nehmen wir dann das Array der Zutaten zurück
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
                                 */
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = {
                                        expanded = !expanded
                                    }
                                ) {
                                    TextField(
                                        modifier = Modifier.menuAnchor(),
                                        readOnly = true,
                                        value = selectedOptionText,
                                        onValueChange = { },
                                        label = { Text("Label") },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
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
                            text = "Welchen Schwierigkeitsgrad möchtest du?",
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
                            val maxValue = 3
                            val values = listOf("egal", "simpel", "mittel", "schwer")

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
                            text = "Welchen Geschmack magst du?",
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
            // Der Suche Button
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ){
                FloatingActionButton(
                    onClick = {navController.navigate("ResultView")}, //andere Seite einfügen
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                ) {
                    Text("Suchen")
                }
            }
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
                        icon = {
                            Icon(
                                icons[index],
                                contentDescription = "Cocktail",
                            )
                        },
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