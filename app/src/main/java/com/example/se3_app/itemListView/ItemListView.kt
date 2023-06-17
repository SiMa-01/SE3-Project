@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.se3_app.itemListView

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.ShoppingListDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.searchCocktailView.font
import com.example.se3_app.startView.Navigationbar
import com.example.se3_app.ui.theme.cardColor2
import com.example.se3_app.ui.theme.cardColor6



var itemList: MutableList<ShoppingListDto> by mutableStateOf(mutableListOf())

@Composable
fun ItemListView(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {
    if (listViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        itemList = listViewModel.userShoppingList
        ItemListViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListViewContent(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {
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

        // ---------------------------------------------------------------------------------------------
        val newItemState = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (itemList[0].list.isNullOrEmpty()){
                Text("Noch keine Zutaten in der Einkaufsliste? \nFüge sie mit dem plus in den Rezepten oder hier im Textefeld hinzu!", fontSize = 20.sp)
            }
            else {

                Text("Zutaten deiner Einkaufsliste ", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                ) {
                    Column {
                        for (item in itemList[0].list!!) {
                            Row(Modifier.padding(8.dp)) {
                                Text(
                                    item,
                                    modifier = Modifier.weight(1f).padding(5.dp)
                                )
                                IconButton(
                                    onClick = {
                                        itemList[0].list!!.remove(item)
                                        listViewModel.deleteShoppingList(listViewModel.userId, item)
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = newItemState.value,
                    onValueChange = { newItemState.value = it },
                    label = { Text("Neue Zutat") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                    containerColor = cardColor2,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = cardColor6,
                    unfocusedIndicatorColor = Color.Gray
                    )
                )

                FloatingActionButton(
                    onClick = {
                        if (newItemState.value != "") {
                            itemList[0].list!!.add(newItemState.value)
                            listViewModel.addShoppingList(listViewModel.userId, newItemState.value)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    containerColor = cardColor6
                ) {
                    Text(text = "Zutat hinzufügen", fontFamily = font)
                }
            }
        }
        }
        Spacer(modifier = Modifier.height(140.dp))
    }
    Navigationbar(viewModel, listViewModel, navController)
}


