package com.example.se3_app.newCocktailView

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.searchCocktailView.font
import com.example.se3_app.searchCocktailView.options
import com.example.se3_app.startView.Navigationbar
import com.example.se3_app.ui.theme.cardColor1
import com.example.se3_app.ui.theme.cardColor2
import com.example.se3_app.ui.theme.cardColor6
import kotlin.random.Random

@Composable
fun NewCocktailView(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {
    if (viewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        options = viewModel.tastes as MutableList<String>
        NewCocktailViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCocktailViewContent(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {
    val context = LocalContext.current

    var nameDto: String?
    var tasteDto: String? = null
    var alcoholicDto: Boolean? = null
    var alcoholicBoolean: Boolean? = null
    var difficultyDto: String? = null
    var difficultyInt: Int? = null
    var preparationDto: String? = null

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
                IconButton(onClick = {
                    navController.navigate("HelpView")
                }) {
                    Icon(Icons.Filled.Info, contentDescription = "Search Icon")
                }
            })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Eigenschaften deines neuen Cocktails:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Input field: name
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
                    containerColor = cardColor2,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = cardColor6,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            nameDto = text.text

            Spacer(modifier = Modifier.height(8.dp))

            // Switch: alcohol
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .height(80.dp)
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
                            .height(80.dp)
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
                                        uncheckedTrackColor = cardColor6,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedBorderColor = Color.Transparent,
                                        checkedTrackColor = cardColor6
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

            // Navigation to ingredient selection
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
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
                                        containerColor = cardColor6
                                    ) {
                                        Text("Zutatenfilter", fontFamily = font)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Switch: difficulty
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
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .height(80.dp)
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
                            .height(80.dp)
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
                                    thumbColor = cardColor6,
                                    activeTrackColor = cardColor1
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
                                text = text
                            )
                        }
                    }
                }
            }

            // Drop Down Menu: Taste
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
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .height(80.dp)
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
                            .height(80.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            var expanded by remember { mutableStateOf(false) }
                            var selectedOptionText by remember {
                                mutableStateOf(
                                    viewModel.comeBack2[3].toString()
                                )
                            }
                            tasteDto = selectedOptionText
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }
                            ) {
                                TextField(
                                    modifier = Modifier.menuAnchor(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = {},
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
                                    onDismissRequest = { expanded = false }
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                        )
                                    }
                                    tasteDto = selectedOptionText
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Input field: description text
            var descriptionText by remember {
                mutableStateOf(
                    TextFieldValue(viewModel.comeBack2[4].toString())
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth().height(300.dp),
                value = descriptionText,
                onValueChange = {
                    descriptionText = it
                    preparationDto = descriptionText.text
                },
                label = { Text(text = "Beschreibe deinen Cocktail") },
                placeholder = {
                    Text(
                        "Nutze für deine Beschreibung am Besten einzelne Schritte. Gebe außerdem die Mengenanzahl deiner Zutaten an. Beispiel:\nSchritt 1: Die beiden Limettenenden der unbehandelten Limetten abschneiden, die Frucht achteln und in ein Tumbler-Glas geben. \nSchritt 2: Danach 2EL braunen Zucker drüber verteilen und die Limettenstücke mit einem Stößel leicht ausdrücken. \nSchritt 3: 6cl Cachaca dazugeben und das Glas mit 5EL Crushed Ice auffüllen - alles gut durchrühren.\nSchritt 4: Eventuell noch einen Schuss Soadwasser hinzufügen und den Cocktail mit einem Trinkhalm servieren. ",
                        fontSize = 10.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = cardColor2,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = cardColor6,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            preparationDto = descriptionText.text

            Spacer(modifier = Modifier.height(8.dp))

            // Search Button
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                FloatingActionButton(
                    onClick = {
                        val newCocktail = CocktailDto(
                            Random.nextInt(0, 1000 + 1).toString(),
                            nameDto,
                            viewModel.selectedIngredients.toTypedArray(),
                            difficultyDto,
                            alcoholicDto!!,
                            tasteDto,
                            preparationDto
                        )
                        if (text.text.isNullOrEmpty() || viewModel.selectedIngredients.isNullOrEmpty() || descriptionText.text.isNullOrEmpty()) {
                            Toast.makeText(
                                context,
                                "Der Name, Zutaten und die Beschreibung dürfen nicht leer sein!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.addCocktail(newCocktail)
                            if (viewModel.loading) {
                                var i = 0
                                i++
                            } else {
                                Toast.makeText(
                                    context,
                                    "Neuer Cocktail mit dem Namen ${text.text} angelegt",
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModel.selectedIngredients.clear()
                            }
                        }
                    },
                    modifier = Modifier
                        .height(40.dp).fillMaxWidth(),
                    containerColor = cardColor6
                ) {
                    Text("Hinzufügen")
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
    Navigationbar(viewModel, listViewModel, navController)
}
