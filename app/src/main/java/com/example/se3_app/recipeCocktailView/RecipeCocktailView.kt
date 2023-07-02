package com.example.se3_app.recipeCocktailView

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.Cocktailbox
import com.example.se3_app.startView.Navigationbar

var cocktail = emptyList<CocktailDto>()
var itemList: MutableList<ShoppingListDto> by mutableStateOf(mutableListOf())

@Composable
fun RecipeCocktailView(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
    if (viewModel.loading || listViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize().testTag("Rezeptview"),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        cocktail = viewModel.cocktailByName
        itemList = listViewModel.userShoppingList
        RecipeCocktailViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeCocktailViewContent(
    navController: NavController,
    viewModel: MainViewModel,
    listViewModel: ListViewModel
) {
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

        // ----------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Cocktailbox(
                navController,
                viewModel,
                cocktail[0],
                1,
                listViewModel
            )

            Text("Für deinen ${cocktail[0].name} brauchst du: ", fontSize = 20.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Column {
                    for (item in cocktail[0].ingredients!!) {
                        Row(
                            modifier = Modifier
                                .border(BorderStroke(1.dp, Color.LightGray))
                                .padding(8.dp)
                        ) {
                            Text(
                                item,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    listViewModel.addShoppingList(listViewModel.userId, item)
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Hinzufügen"
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Die Zubereitung umfasst folgende Schritte: ", fontSize = 20.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(Modifier.padding(8.dp)) {
                    Text(
                        text = cocktail[0].preparation.toString()
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
    Navigationbar(viewModel, listViewModel, navController)
}
