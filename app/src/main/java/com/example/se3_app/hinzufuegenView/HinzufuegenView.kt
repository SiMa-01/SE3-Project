package com.example.se3_app.hinzufuegenView

import android.widget.Toast
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.cocktailSearchView.font
import com.example.se3_app.cocktailSearchView.options
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.ui.theme.chipFarbe1
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe6
import kotlin.random.Random


//val newCocktail = AddCocktailDto("", emptyArray(),"", true, "", "")
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
        options = viewModel.tastes as MutableList<String>
        HinzufuegenViewContent(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HinzufuegenViewContent(navController: NavController, viewModel: MainViewModel) {

    val context  = LocalContext.current

    var nameDto: String? = null
    var tasteDto: String? = null
    var alcoholicDto: Boolean? = null
    var alcoholicBoolean: Boolean? = null
    var difficultyDto: String? = null
    var difficultyInt: Int? = null
    var preparationDto: String? = null

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
            var text by remember { mutableStateOf(TextFieldValue(viewModel.comeBack2[0].toString())) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    nameDto = text.text
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
            nameDto = text.text

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

                            var switchOn by remember {
                                mutableStateOf(viewModel.comeBack2[1] as Boolean)
                            }

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Switch(
                                    checked = switchOn,
                                    onCheckedChange = { switchOn_ ->
                                        switchOn = switchOn_
                                    },
                                    colors = SwitchDefaults.colors(
                                        uncheckedTrackColor = chipFarbe6,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedBorderColor = Color.Transparent,
                                        checkedTrackColor = chipFarbe6
                                    )
                                )
                                alcoholicBoolean = switchOn
                                alcoholicDto = switchOn
                                Text(text = if (switchOn) "ja" else "nein")
                            }
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
                                            viewModel.cameFrom = 2
                                            if (nameDto != null) viewModel.comeBack2[0] = nameDto.toString()
                                            if (alcoholicBoolean != null) viewModel.comeBack2[1] = alcoholicBoolean!!
                                            if (difficultyInt != null) viewModel.comeBack2[2] = difficultyInt!!
                                            if (tasteDto != null) viewModel.comeBack2[3] = tasteDto.toString()
                                            if (preparationDto != null) viewModel.comeBack2[4] = preparationDto.toString()

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
                            val istValue: Int = viewModel.comeBack2[2] as Int
                            val values = listOf("simpel", "mittel", "schwer")

                            val selectedValue = remember { mutableStateOf(istValue) }
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
                                steps = maxValue - minValue - 1
                            )

                            val text = values[selectedValue.value]
                            difficultyDto = values[selectedValue.value]
                            difficultyInt = selectedValue.value
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize(), contentAlignment = Alignment.Center
                        ) {

                            var expanded by remember { mutableStateOf(false) }
                            var selectedOptionText by remember { mutableStateOf(viewModel.comeBack2[3].toString()) }
                            tasteDto = selectedOptionText
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded },
                            ) {
                                TextField(
                                    modifier = Modifier.menuAnchor(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = {},
                                    label = { Text("Label") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                                println("Der ausgewählte Geschmack: "+ selectedOptionText)
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                        )
                                    }
                                    tasteDto = selectedOptionText
                                }
                            }


                            /*
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
                                    value = selectedOptionText,
                                    onValueChange = {},
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
                                                tasteDto = selectionOption
                                            }
                                        )
                                    }
                                }
                            }*/
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Eingabefeld des Nutzers
            var beschreibungsText by remember { mutableStateOf(TextFieldValue(viewModel.comeBack2[4].toString())) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth().height(300.dp),
                value = beschreibungsText,
                onValueChange = {
                    beschreibungsText = it
                    preparationDto = beschreibungsText.text
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
            preparationDto = beschreibungsText.text

            Spacer(modifier = Modifier.height(8.dp))

            // Der Suche Button
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                FloatingActionButton(
                    onClick = {
                        val newCocktail = CocktailDto(Random.nextInt(0, 1000 + 1).toString(), nameDto, viewModel.selectedIngredients.toTypedArray(), difficultyDto, alcoholicDto!!, tasteDto, preparationDto)
                        if(text.text.isNullOrEmpty() || viewModel.selectedIngredients.isNullOrEmpty() || beschreibungsText.text.isNullOrEmpty()){
                            Toast.makeText(context, "Der Name, Zutaten und die Beschreibung dürfen nicht leer sein!", Toast.LENGTH_SHORT).show()
                        } else {
                            println("Neuer Cocktail $newCocktail")
                            viewModel.addCocktail(newCocktail)
                            if (viewModel.loading) {
                                    var i = 0
                                    i++
                            } else {
                                println("Cocktail added ${viewModel.addedCocktail}")
                                Toast.makeText(context, "Neuer Cocktail mit dem Namen ${text.text} angelegt", Toast.LENGTH_SHORT).show()
                                viewModel.selectedIngredients.clear()
                            }
                        }
                              }, //andere Seite einfügen
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