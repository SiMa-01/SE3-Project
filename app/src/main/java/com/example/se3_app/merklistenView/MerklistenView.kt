package com.example.se3_app.merklistenView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.startView.Cocktailbox
import com.example.se3_app.startView.CocktailboxMitIndex


@Composable
fun MerklistenView(navController: NavController, viewModel: MainViewModel) {
    MerklistenViewContent(navController, viewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MerklistenViewContent(navController: NavController, viewModel: MainViewModel) {
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



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text("Deine Lieblingscocktails:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            val name="Caipirinha"
            val difficulty="MEDIUM"
            var alcoholic by remember { mutableStateOf(true)}
            val taste ="Sour"

            MerklistenCocktailbox(
                navController,
                viewModel,
                name,
                difficulty,
                alcoholic,
                taste,
                1
            )
            MerklistenCocktailbox(
                navController = navController,
                viewModel = viewModel,
                name ="Caipirinha" ,
                difficulty ="MEDIUM" ,
                alcoholic = true,
                taste ="Sour",
                2
            )
            MerklistenCocktailbox(
                navController = navController,
                viewModel = viewModel,
                name ="Caipirinha" ,
                difficulty ="MEDIUM" ,
                alcoholic = true,
                taste ="Sour",
                3
            )
            MerklistenCocktailbox(
                navController = navController,
                viewModel = viewModel,
                name ="Caipirinha" ,
                difficulty ="MEDIUM" ,
                alcoholic = true,
                taste ="Sour",
                4
            )
            Spacer(modifier = Modifier.height(100.dp))

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
                            selectedItem = 2
                            navigateToDestination(navController, index)
                        }
                    )
                }
            }
        }
    }
@Composable
fun MerklistenCocktailbox(navController: NavController, viewModel: MainViewModel , name: String, difficulty: String, alcoholic: Boolean, taste: String, index: Int) {
    if (viewModel.errorMessage.isEmpty()) {
        CocktailboxMitIndex(navController, viewModel, name, difficulty, alcoholic, taste, index)
    }
}




