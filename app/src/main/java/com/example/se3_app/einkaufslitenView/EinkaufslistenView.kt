@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.se3_app.einkaufslitenView

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
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
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe6


var itemList: MutableList<ShoppingListDto> by mutableStateOf(mutableListOf())

@Composable
fun EinkaufslistenView(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {
    if (listViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        itemList = listViewModel.userShoppingList
        EinkaufsListenViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EinkaufsListenViewContent(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {



    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Favorite,
        Icons.Filled.List
    )
    // Container mit allen Elementen
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Zutaten deiner Einkaufsliste ", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                   // .border(BorderStroke(1.dp, Color.LightGray))
            ) {

                val newItemState = remember { mutableStateOf("") }

                Column {
                    for (item in itemList[0].list!!) {
                        println("hoallo ich bin hier " + itemList[0].list!!)
                        Row (Modifier.padding(8.dp)){
                            Text(
                                item,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    itemList[0].list!!.remove(item)
                                    listViewModel.deleteShoppingList(listViewModel.userId, item)
                                    println("In der remove " + itemList[0].list)
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

                    TextField(
                        value = newItemState.value,
                        onValueChange = { newItemState.value = it },
                        label = { Text("Neue Zutat") },
                        modifier = Modifier.padding(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = chipFarbe2,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = chipFarbe6,
                            unfocusedIndicatorColor = Color.Gray
                        )
                    )
                    FloatingActionButton(
                        onClick = {
                            if (newItemState.value != "") {
                                itemList[0].list!!.add(newItemState.value)
                                listViewModel.addShoppingList(listViewModel.userId, newItemState.value)

                                //navController.navigate("EinkaufslistenView")
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        containerColor = chipFarbe6
                    ) {
                        Text(text = "Zutat hinzufügen")
                      /*  LaunchedEffect(loading.value) {
                            if(loading.value){
                                listViewModel.addShoppingList(listViewModel.userId, newItem.value)
                                delay(3000)
                                listViewModel.getShoppingList(listViewModel.userId)
                                delay(3000)
                               // itemList = listViewModel.userShoppingList
                            }
                            newItemState.value = ""
                            loading.value = false
                        }*/
                    }
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
                    icon = { Icon(icons[index], contentDescription = null) },
                    label = { Text(item) },
                    selected = selectedItem == 1,
                    onClick = {
                        if (index == 1) {
                            viewModel.getAllTastes()
                        }
                        selectedItem = 3
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }
}


