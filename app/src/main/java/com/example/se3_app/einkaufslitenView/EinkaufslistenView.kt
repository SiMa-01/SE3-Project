@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.se3_app.einkaufslitenView

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe6


@Composable
fun EinkaufslistenView(navController: NavController, viewModel: MainViewModel) {
    EinkaufsListenViewContent(navController, viewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EinkaufsListenViewContent(navController: NavController, viewModel: MainViewModel) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Search,
        Icons.Filled.Favorite, Icons.Filled.List
    )
    //Container mit allen Elementen
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


        //---------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )

        {

            Text("Zutaten, die du manuelle hinzugefügt hast ", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
            )
            {


                val itemList by remember {
                    mutableStateOf(
                        mutableStateListOf(
                            "Zutat 1",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3",
                            "Zutat 2",
                            "Zutat 3"
                        )
                    )
                }
                val newItemState = remember { mutableStateOf("") }

                //TODO Methode auslagern
                fun removeItem(item: String) {
                    itemList.remove(item)
                }


                Column {

                    for (item in itemList) {
                        Row {
                            Text(
                                item,
                                modifier = Modifier.clickable {
                                    removeItem(item)
                                    //    showToast(context, "$item wurde von der Liste entfernt")
                                    // TODO mit dem context habe ich ein Importproblem, keine Ahnung wie ich das löse
                                },

                                )
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
                            val newItem = newItemState.value
                            if (newItem.isNotBlank()) {
                                itemList.add(newItem)
                                newItemState.value = ""
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        containerColor = chipFarbe6
                    ) {
                        Text(text = "Zutat hinzufügen")
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

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
























