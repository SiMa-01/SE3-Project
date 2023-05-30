@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.se3_app.einkaufslitenView

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

@Composable
fun EinkaufslistenView(navController: NavController, viewModel: EinkaufslistenViewModel) {
    EinkaufsListenViewContent(navController, viewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EinkaufsListenViewContent(navController: NavController, viewModel: EinkaufslistenViewModel) {
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
    }

    //---------------------------------------------------------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
           //.verticalScroll(rememberScrollState())
    ) {
        Text("Liste der gemerkten Zutaten: ", fontSize = 20.sp)
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.LightGray))
                .fillMaxWidth()
                .padding(bottom= 50.dp).padding(5.dp)
        ) {
            val gemerkteZutaten = listOf("Gin", "Tonic", "Eis")
            ListScreen(gemerkteZutaten)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Zutaten, die du manuelle hinzugefügt hast ", fontSize = 20.sp)
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.LightGray))
                .fillMaxWidth()
        ) {
            EditierbaereListe()
            }
    }

        Spacer(modifier = Modifier.height(100.dp))

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
                            selectedItem = 3
                            navigateToDestination(navController, index)
                        }
                    )
                }
            }
        }
    }


@Composable
fun ListScreen(zutaten: List<String>) {
       LazyColumn {
        items(zutaten) { zutaten ->
            ListItem(zutaten)
        }
    }
}
@Composable
fun ListItem(zutaten: String) {
        Text(text = zutaten)
}
@Composable
fun EditierbaereListe() {
    var itemList by remember { mutableStateOf(mutableStateListOf("Zutat 1", "Zutat 2", "Zutat 3")) }
    val newItemState = remember { mutableStateOf("") }

    fun removeItem(item: String) {
        itemList.remove(item)
    }


    Column {
        LazyColumn(

        ) {
            items(itemList) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .clickable { removeItem(item) }
                        .padding(5.dp)
                )
            }
        }
        TextField(
            value = newItemState.value,
            onValueChange = { newItemState.value = it },
            label = { Text("Neue Zutat") },
            modifier = Modifier.padding(16.dp)
        )
            FloatingActionButton(
                onClick = {
                    val newItem = newItemState.value
                    if (newItem.isNotBlank()) {
                        itemList.add(newItem)
                        newItemState.value = ""
                    }
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Text(text = "Zutat hinzufügen")
            }


        }
    }







