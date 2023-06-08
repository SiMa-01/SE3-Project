package com.example.se3_app.rezeptView

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.MainViewModel
import com.example.se3_app.ingredientsView.ChipEachRow
import com.example.se3_app.startView.Cocktailbox
import com.example.se3_app.startView.navigateToDestination

var cocktail = emptyList<CocktailDto>()

@Composable
fun RezeptView(navController: NavController, viewModel: MainViewModel) {

    if (viewModel.loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        cocktail = viewModel.cocktailByName
        RezeptViewContent(navController, viewModel)
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RezeptViewContent(navController: NavController, viewModel: MainViewModel) {
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

        //----------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val name= cocktail[0].name
            var ingredients= cocktail[0].ingredients
            val difficulty=cocktail[0].difficulty
            var alcoholic = cocktail[0].alcoholic
            val taste =cocktail[0].taste

            RezeptCocktailbox(
                navController,
                viewModel,
                name!!,
                ingredients!!,
                difficulty!!,
                alcoholic,
                taste!!
            )

            Text("Für deinen ${cocktail[0].name} brauchst du: ", fontSize = 20.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {

                Column {

                    for (item in cocktail[0].ingredients!!) {
                        Row(Modifier.padding(8.dp)) {
                            Text(
                                item,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                  //        showToastMessage("$item wurde hinzugefügt")
                                    // TODO hier kommt das hinzufügen zur Einkaufsliste
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
                    .padding(horizontal = 5.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
                Text(
                    text = cocktail[0].preparation.toString()
                )
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
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }

}


@Composable
fun RezeptCocktailbox(navController: NavController, viewModel: MainViewModel, name: String, ingredients: Array<String>, difficulty: String, alcoholic: Boolean, taste: String) {
    if (viewModel.errorMessage.isEmpty()) {
        Cocktailbox(navController, viewModel, name, ingredients, difficulty, alcoholic, taste)
    }
}
@Composable
fun showToastMessage(message:String){
    val context= LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}