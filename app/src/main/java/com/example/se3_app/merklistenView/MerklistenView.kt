package com.example.se3_app.merklistenView

import android.annotation.SuppressLint
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.FavoriteCocktailDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.Cocktailbox
import com.example.se3_app.startView.navigateToDestination


var favoriteList: MutableList<FavoriteCocktailDto> by mutableStateOf(mutableListOf())

@SuppressLint("UnrememberedMutableState")
@Composable
fun MerklistenView(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {


    if (listViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        favoriteList = listViewModel.userFavoriteList
        MerklistenViewContent(navController, viewModel, listViewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MerklistenViewContent(navController: NavController, viewModel: MainViewModel, listViewModel: ListViewModel) {

    println("Die ID " + listViewModel.userId)
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
            IconButton(onClick = { navController.navigate("HelpView") })
            {
                Icon(Icons.Filled.Info, contentDescription = "Search Icon")
            }
        })



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text("Deine Lieblingscocktails:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            favoriteList[0].list.forEachIndexed{ index, s ->
                Cocktailbox(
                    navController,
                    viewModel,
                    favoriteList[0].list[index],
                    index,
                    listViewModel
                )
            }

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
                            if (index == 1) {
                                viewModel.getAllTastes()
                            }
                            selectedItem = 2
                            navigateToDestination(navController, index)
                        }
                    )
                }
            }
        }
    }






