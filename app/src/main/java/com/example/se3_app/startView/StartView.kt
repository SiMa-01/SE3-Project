package com.example.se3_app.startView

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.resultView.ResultCocktailbox
import com.example.se3_app.resultView.ResultViewContent
import com.example.se3_app.resultView.cocktails
import com.example.se3_app.ui.theme.chipFarbe1
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe3
import com.example.se3_app.ui.theme.chipFarbe4
import com.example.se3_app.ui.theme.chipFarbe5
import com.example.se3_app.ui.theme.chipFarbe6


fun generateRandomNumbers(): List<Int> {
    val randomNumbers = mutableListOf<Int>()

    while (randomNumbers.size < 8) {
        val randomNumber = (0..30).random()
        if (!randomNumbers.contains(randomNumber)) {
            randomNumbers.add(randomNumber)
        }
    }

    return randomNumbers
}

val randomList = generateRandomNumbers()
val font = FontFamily(
    Font(resId = R.font.arciform)
)

@Composable
fun StartView(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    StartViewContent(navController, viewModel, listViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartViewContent(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    // Das müssen wir vermutlich überall hin kopieren

    val buttonFarbe = Color(0x6A6C84FF)
    val context = LocalContext.current

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons =
        listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Favorite, Icons.Filled.List)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceBetween,
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
                    fontFamily = font,
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

        //--------------------------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Der MIX'N'FIX", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            println("drink ${viewModel.cocktailsAll}")
            val name = viewModel.cocktailsAll[0].name
            var ingredients = viewModel.cocktailsAll[0].ingredients
            val difficulty = viewModel.cocktailsAll[0].difficulty
            var alcoholic = viewModel.cocktailsAll[0].alcoholic
            val taste = viewModel.cocktailsAll[0].taste

            StartCocktailbox(
                navController, viewModel, name!!, difficulty!!, alcoholic, taste!!, 4, listViewModel
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Funktionen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.selectedIngredients.clear()
                        viewModel.comeBack = arrayOf("", 0f, 0, "egal", "")
                        viewModel.getAllTastes()
                        navController.navigate("CocktailSearchView")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    containerColor = chipFarbe2
                ) {
                    Text("Cocktail suchen", fontFamily = font, fontSize = 15.sp)
                }
                FloatingActionButton(
                    onClick = {
                        viewModel.comeBack2 = arrayOf("", false, 0, "bitter", "")
                        viewModel.getAllTastes()
                        viewModel.selectedIngredients.clear()
                        navController.navigate("HinzufuegenView")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp),
                    containerColor = chipFarbe2
                ) {
                    Text(
                        "Rezept hinzufügen", fontFamily = font, fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Zum inspirieren", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            var i: Int = 0
            while (i < 8) {
                ResultCocktailbox(
                    navController,
                    viewModel,
                    viewModel.cocktailsAll[i].name.toString(),
                    viewModel.cocktailsAll[i].difficulty.toString(),
                    viewModel.cocktailsAll[i].alcoholic,
                    viewModel.cocktailsAll[i].taste.toString(),
                    i,
                    listViewModel
                )
                i++
            }

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
                NavigationBarItem(icon = { Icon(icons[index], contentDescription = "Home") },
                    label = { Text(item) },
                    selected = selectedItem == 1,
                    onClick = {
                        if (index == 1) {
                            viewModel.comeBack = arrayOf("", 0f, 0, "egal", "")
                            viewModel.selectedIngredients.clear()
                            viewModel.getAllTastes()
                        } else if (index == 2) {
                            listViewModel.getFavouriteList(listViewModel.userId)
                        }
                        selectedItem = index
                        navigateToDestination(navController, index)
                    })
            }
        }

    }
}

// Das ist die Methode nur für diese View
@Composable
fun StartCocktailbox(
    navController: NavController,
    viewModel: MainViewModel,
    name: String,
    difficulty: String,
    alcoholic: Boolean,
    taste: String,
    index: Int,
    listViewModel: ListViewModel
) {
    if (viewModel.errorMessage.isEmpty()) {
        CocktailboxMitIndex(
            navController,
            viewModel,
            name,
            difficulty,
            alcoholic,
            taste,
            index,
            listViewModel
        )
    }
}


@Composable
fun CocktailboxMitIndex(
    navController: NavController,
    viewModel: MainViewModel,
    name: String,
    difficulty: String,
    alcoholic: Boolean,
    taste: String,
    index: Int,
    listViewModel: ListViewModel
) {
    val color: Color;
    if (index % 6 == 0) color = chipFarbe1
    else if (index % 5 == 0) color = chipFarbe2
    else if (index % 4 == 0) color = chipFarbe3
    else if (index % 3 == 0) color = chipFarbe4
    else if (index % 2 == 0) color = chipFarbe5
    else color = chipFarbe6

    Cocktailbox(navController, viewModel, name, difficulty, alcoholic, taste, color, listViewModel)
}

@Composable
fun Cocktailbox(
    navController: NavController,
    viewModel: MainViewModel,
    name: String,
    difficulty: String,
    alcoholic: Boolean,
    taste: String,
    color: Color,
    listViewModel: ListViewModel
) {



    var ingredient = arrayOf<String>(" ")
    var cocktail: MutableList<CocktailDto> =
        mutableListOf(CocktailDto("", "", ingredient, "", false, "", ""))
    val context = LocalContext.current


    FloatingActionButton(
        onClick = {
            viewModel.getCocktailByName(name)
            navController.navigate("RezeptView")
        },
        modifier = Modifier.fillMaxWidth(),
        containerColor = color
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 5.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cocktail),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(70.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {
                        // Name des Cocktails und Stern nebeneinander
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = name,
                                fontFamily = font,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            //wenn der Button geklickt wird -> zur Merkliste hinzufügen

                            var isClicked= remember { mutableStateOf(false) }
                            var loading = remember { mutableStateOf(false) }
                            
                            IconButton(
                                onClick = {
                                    loading.value = true
                                    isClicked.value = !isClicked.value
                                },
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(1f)
                            ) {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Zur Merkliste",
                                    tint = if (isClicked.value) Color.Red else Color.Unspecified
                                )
                                LaunchedEffect(loading.value) {
                                    if(isClicked.value && loading.value){
                                        viewModel.getCocktailByName(name)
                                        delay(3000)
                                        listViewModel.addFavoritList(
                                            listViewModel.userId,
                                            viewModel.cocktailByName[0]
                                        )
                                    } else if (!isClicked.value && loading.value){
                                        listViewModel.deleteFavoritList(listViewModel.userId, viewModel.cocktailByName[0]._id)
                                        delay(3000)
                                    }
                                    loading.value = false
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Die weiteren Informationen stehen nebeneinander unter dem Namen
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                //Den Schwierigkeitsgrad durch Farben darstellen
                                if (difficulty == "einfach") {/*  Box(
                                          modifier = Modifier
                                              .size(10.dp)
                                              .background(green)
                                      )*/
                                    Icon(
                                        painter = painterResource(id = R.drawable.easy),
                                        contentDescription = "einfach",
                                        modifier = Modifier.size(30.dp)
                                    )

                                } else if (difficulty == "mittel") {
                                    Row() {
                                        /* Box(
                                             modifier = Modifier
                                                 .size(10.dp)
                                                 .background(orange)
                                         )*/
                                        Icon(
                                            painter = painterResource(id = R.drawable.medium),
                                            contentDescription = "mittel",
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }

                                } else if (difficulty == "schwierig") {/*Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .background(Color.Red)
                                    )*/
                                    Icon(
                                        painter = painterResource(id = R.drawable.hard),
                                        contentDescription = "schwierig",
                                        modifier = Modifier.size(30.dp)
                                    )

                                }
                            }
                            //Die Schwierigkeit daneben schreiben
                            /*Box() {
                                if (difficulty == "schwierig"){
                                    Text(
                                        text = "schwer",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(3.dp))
                                }
                                else {
                                    Text(
                                        text = difficulty,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(3.dp))
                                }
                            }*/

                            //Den Alkoholgehalt durch Alkohol darstellen
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                if (alcoholic) {
                                    Text(
                                        "%", fontFamily = font, fontSize = 15.sp
                                    )
                                } else {
                                    Text(
                                        "kein %", fontFamily = font, fontSize = 15.sp
                                    )
                                }
                            }
                            //Den Geschmack nennen
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(taste, fontSize = 15.sp)
                            }

                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}


fun navigateToDestination(navController: NavController, index: Int) {
    when (index) {
        0 -> navController.navigate("StartView")
        1 -> navController.navigate("CocktailSearchView")
        2 -> navController.navigate("MerklistenView")
        3 -> navController.navigate("EinkaufslistenView")
    }
}

suspend fun loading() {
    delay(3000)
}




