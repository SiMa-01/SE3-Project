package com.example.se3_app.EinkaufslitenView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.View.StartView.navigateToDestination

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
           //.verticalScroll(rememberScrollState())
    ) {
        Text("Liste der gemerkten Zutaten: ", fontSize = 20.sp)

        Box(
            modifier = Modifier
                       .border(BorderStroke(1.dp, Color.LightGray)).fillMaxWidth()
        ) {
            val names = listOf("Gin", "Tonic", "Eis")
            ListScreen(names)
        }
        Text("Liste deiner manuell hinzugefügten gemerkten Zutaten: ", fontSize = 20.sp)

        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.LightGray)).fillMaxWidth()
        ) {
            val names = listOf("Gin", "Tonic", "Eis")
            ListScreen(names)
        }
    }
        /*
        //Des hier soll eine normale Liste sein, aber die will nicht wie ich des möchte

        @Composable
        fun gemerkteZutatenList() {
            val zutatenlist = listOf("Gin", "Vodka", "Eis", "Limetten", "O-Saft")

            LazyColumn {
                items(zutatenlist) { item ->
                    Text(item)
                }
            }
        }
        @ExperimentalComposeUiApi
        @Composable
        fun warumgehtdermuellnet(){
            MaterialTheme{
                gemerkteZutatenList()
            }
        }

        //Des soll eine editierbare Liste sein, aber die will ebenfalls net wie ich des möchte
        Text("Liste der manuell hinzugefügten Zutaten: ", fontSize = 20.sp)
        @Composable
        fun EditableList() {
            // Hier wird die Liste der Elemente gespeichert
            var itemList by remember { mutableStateOf(mutableStateListOf<String>()) }

            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Eingabefeld für den neuen Eintrag
                    var newItemText by remember { mutableStateOf("") }
                    TextField(
                        value = newItemText,
                        onValueChange = { newItemText = it },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text("Neuer Eintrag") }
                    )

                    // Schaltfläche zum Hinzufügen des Eintrags
                    Button(
                        onClick = {
                            if (newItemText.isNotBlank()) {
                                itemList.add(newItemText)
                                newItemText = ""
                            }
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Hinzufügen")
                    }
                }

                // Anzeigen der Liste der Elemente
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(itemList.size) { index ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(itemList[index])

                            // Schaltfläche zum Entfernen des Eintrags
                            Button(
                                onClick = { itemList.removeAt(index) },
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                Text("Entfernen")
                            }
                        }
                    }
                }
            }
        }

*/
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
            // Hier wird jedes Element der Liste angezeigt
            ListItem(zutaten)
        }
    }
}
@Composable
fun ListItem(zutaten: String) {
        Text(text = zutaten)
}


