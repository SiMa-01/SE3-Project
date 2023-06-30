package com.example.se3_app.searchCocktailView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.Navigationbar
import com.example.se3_app.ui.theme.cardColor1
import com.example.se3_app.ui.theme.cardColor2
import com.example.se3_app.ui.theme.cardColor6

var options = mutableListOf<String>()

@Composable
fun SearchCocktailView(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    if (viewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        options = viewModel.tastes as MutableList<String>
        SearchCocktailViewContent(navController, viewModel, listViewModel)
    }
}

val font = FontFamily(
    Font(resId = R.font.arciform)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCocktailViewContent(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    var nameDto: String? = null
    var tasteDto: String? = null
    var alcoholicDto: String? = null
    var alcoholicFloat: Float? = null
    var difficultyDto: String? = null
    var difficultyInt: Int? = null

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.testTag("searchCocktailView")
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
            Text(
                "Wähle alle Einschränkungen für deinen Cocktail aus:",
                fontFamily = font,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
                var name by remember {
                    mutableStateOf(
                        TextFieldValue(viewModel.comeBack[0].toString())
                    )
                }

                TextField(

                    value = name,

                    onValueChange = { newValue ->
                        name = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text(text = "Nach Name filtern", fontFamily = font) },
                    placeholder = { Text(text = "Cocktainame", fontFamily = font) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = cardColor2,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = cardColor6,
                        unfocusedIndicatorColor = Color.Gray
                    )
                )
                nameDto = name.text
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
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .height(80.dp)
                    ) {
                        Text(
                            text = "Soll der Cocktail Alkohol enthalten?",
                            fontFamily = font,
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
                            val istValue: Float = viewModel.comeBack[1] as Float
                            val values = listOf("egal", "ja", "nein")

                            var alcoholic = remember { mutableStateOf(istValue) }

                            Slider(
                                value = alcoholic.value,
                                colors = SliderDefaults.colors(
                                    thumbColor = cardColor6,
                                    activeTrackColor = cardColor1
                                ),
                                onValueChange = { newValue ->
                                    alcoholic.value = newValue
                                },
                                valueRange = minValue.toFloat()..maxValue.toFloat(),
                                steps = maxValue - minValue - 1
                            )

                            val text = values[alcoholic.value.toInt()]
                            alcoholicDto = values[alcoholic.value.toInt()]
                            alcoholicFloat = alcoholic.value
                            Text(
                                text = text,
                                fontFamily = font
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Choose the incredients
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
                            text = "Welche Zutaten soll der Cocktail enthalten?",
                            fontFamily = font,
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
                                            viewModel.cameFrom = 1
                                            if (nameDto != null) viewModel.comeBack[0] = nameDto.toString()
                                            if (alcoholicFloat != null) viewModel.comeBack[1] = alcoholicFloat!!
                                            if (difficultyInt != null) viewModel.comeBack[2] = difficultyInt!!
                                            if (tasteDto != null) viewModel.comeBack[3] = tasteDto.toString()
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

            // Choose the difficulty
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
                            text = "Welchen Schwierigkeitsgrad möchtest du?",
                            fontFamily = font,
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
                            val maxValue = 3
                            val istValue: Int = viewModel.comeBack[2] as Int
                            val values = listOf("egal", "simpel", "mittel", "schwer")

                            val difficulty = remember { mutableStateOf(istValue) }
                            Slider(
                                value = difficulty.value.toFloat(),
                                colors = SliderDefaults.colors(
                                    thumbColor = cardColor6,
                                    activeTrackColor = cardColor1
                                ),
                                onValueChange = { newValue ->
                                    difficulty.value = newValue.toInt()
                                },
                                valueRange = minValue.toFloat()..maxValue.toFloat(),
                                steps = maxValue - minValue - 1
                            )

                            val text = values[difficulty.value]
                            difficultyDto = values[difficulty.value]
                            difficultyInt = difficulty.value
                            Text(
                                text = text,
                                fontFamily = font
                            )
                        }
                    }
                }
            }

            // Choose the taste
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
                            text = "Welchen Geschmack magst du?",
                            fontFamily = font,
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
                                    viewModel.comeBack[3].toString()
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
                                    DropdownMenuItem(
                                        text = { Text(text = "egal") },
                                        onClick = {
                                            selectedOptionText = "egal"
                                            expanded = false
                                        }
                                    )
                                    tasteDto = selectedOptionText
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Search Button
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.searchCocktails(
                            nameDto,
                            tasteDto,
                            viewModel.selectedIngredients,
                            alcoholicDto,
                            difficultyDto
                        )
                       // listViewModel.getShoppingList(listViewModel.userId)
                        navController.navigate("ResultView")
                        viewModel.selectedIngredients.clear()
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth().testTag("searchButton"),
                    containerColor = cardColor6
                ) {
                    Text("Suchen", fontFamily = font)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
    Navigationbar(viewModel, listViewModel, navController)
}
