package com.example.se3_app.startView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.favoriteListView.favoriteList
import com.example.se3_app.ui.theme.cardColor1
import com.example.se3_app.ui.theme.cardColor2
import com.example.se3_app.ui.theme.cardColor3
import com.example.se3_app.ui.theme.cardColor4
import com.example.se3_app.ui.theme.cardColor5
import com.example.se3_app.ui.theme.cardColor6
import java.util.*
import kotlinx.coroutines.*

val font = FontFamily(
    Font(resId = R.font.arciform)
)

@Composable
fun StartView(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    if (listViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        favoriteList = listViewModel.userFavoriteList
        StartViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartViewContent(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
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

        // --------------------------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Der MIX'N'FIX", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Cocktailbox(
                navController,
                viewModel,
                viewModel.cocktailsAll[0],
                4,
                listViewModel
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
                        navController.navigate("SearchCocktailView")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    containerColor = cardColor2
                ) {
                    Text("Cocktail suchen", fontFamily = font, fontSize = 15.sp)
                }
                FloatingActionButton(
                    onClick = {
                        viewModel.comeBack2 = arrayOf("", false, 0, "bitter", "")
                        viewModel.getAllTastes()
                        viewModel.selectedIngredients.clear()
                        navController.navigate("newCocktailnView")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(80.dp),
                    containerColor = cardColor2
                ) {
                    Text(
                        "Rezept hinzufügen",
                        fontFamily = font,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Zum inspirieren", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            var i = 0
            while (i < 8) {
                Cocktailbox(
                    navController,
                    viewModel,
                    viewModel.cocktailsAll[i],
                    i,
                    listViewModel
                )
                i++
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
    Navigationbar(viewModel, listViewModel, navController)
}

@Composable
fun Navigationbar(
    viewModel: MainViewModel,
    listViewModel: ListViewModel,
    navController: NavController
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons =
        listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Favorite, Icons.Filled.List)
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
                        if (index == 1) {
                            viewModel.comeBack = arrayOf("", 0f, 0, "egal", "")
                            viewModel.selectedIngredients.clear()
                            viewModel.getAllTastes()
                        } else if (index == 3) {
                            listViewModel.getShoppingList(listViewModel.userId)
                        }
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }
}

@Composable
fun Cocktailbox(
    navController: NavController,
    viewModel: MainViewModel,
    cocktail: CocktailDto,
    colorIndex: Int,
    listViewModel: ListViewModel
) {
    val color: Color
    if (colorIndex % 6 == 0) {
        color = cardColor1
    } else if (colorIndex % 5 == 0) {
        color = cardColor2
    } else if (colorIndex % 4 == 0) {
        color = cardColor3
    } else if (colorIndex % 3 == 0) {
        color = cardColor4
    } else if (colorIndex % 2 == 0) {
        color = cardColor5
    } else {
        color = cardColor6
    }

    FloatingActionButton(
        onClick = {
            viewModel.getCocktailByName(cocktail.name!!)
            navController.navigate("RezeptView")
        },
        modifier = Modifier.fillMaxWidth(),
        containerColor = color
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 5.dp)

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
                        // Name of the cocktail and star next to each other
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cocktail.name!!,
                                fontFamily = font,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            // Buttonclick for adding to favorite list

                            val isClicked = remember { mutableStateOf(false) }

                            favoriteList[0].list.forEachIndexed { index, s ->
                                if (favoriteList[0].list[index].name.equals(cocktail.name)) {
                                    isClicked.value = true
                                }
                            }

                            IconButton(
                                onClick = {
                                    isClicked.value = !isClicked.value

                                    if (isClicked.value) {
                                        favoriteList[0].list.add(cocktail)
                                        listViewModel.addFavoritList(
                                            listViewModel.userId,
                                            cocktail
                                        )
                                    } else if (!isClicked.value) {
                                        favoriteList[0].list.remove(cocktail)
                                        listViewModel.deleteFavoritList(
                                            listViewModel.userId,
                                            cocktail._id
                                        )
                                    }
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
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // detail information
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                if (cocktail.difficulty!! == "einfach") {
                                    Icon(
                                        painter = painterResource(id = R.drawable.easy),
                                        contentDescription = "einfach",
                                        modifier = Modifier.size(30.dp)
                                    )
                                } else if (cocktail.difficulty == "mittel") {
                                    Row() {
                                        Icon(
                                            painter = painterResource(id = R.drawable.medium),
                                            contentDescription = "mittel",
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                } else if (cocktail.difficulty == "schwierig") {
                                    Icon(
                                        painter = painterResource(id = R.drawable.hard),
                                        contentDescription = "schwierig",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }

                            // Alcohol content
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                if (cocktail.alcoholic) {
                                    Text(
                                        "%",
                                        fontFamily = font,
                                        fontSize = 15.sp
                                    )
                                } else {
                                    Text(
                                        "kein %",
                                        fontFamily = font,
                                        fontSize = 15.sp
                                    )
                                }
                            }

                            // Taste
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(cocktail.taste!!, fontSize = 15.sp)
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
        1 -> navController.navigate("SearchCocktailView")
        2 -> navController.navigate("FavoriteListView")
        3 -> navController.navigate("ItemListView")
    }
}

suspend fun loading() {
    delay(3000)
}
