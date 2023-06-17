package com.example.se3_app.favoriteListView

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.Dto.FavoriteCocktailDto
import com.example.se3_app.ListViewModel
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.Cocktailbox
import com.example.se3_app.startView.Navigationbar

var favoriteList: MutableList<FavoriteCocktailDto> by mutableStateOf(mutableListOf())

@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoriteListView(
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
        FavoriteListViewContent(navController, viewModel, listViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListViewContent(
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
                IconButton(onClick = { navController.navigate("HelpView") }) {
                    Icon(Icons.Filled.Info, contentDescription = "Search Icon")
                }
            })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (favoriteList[0].list.isNullOrEmpty()) {
                Text(
                    "Noch keine Lieblingscocktails? \nFüge sie mit dem Herz Icon hinzu!",
                    fontSize = 20.sp
                )
            } else {
                Text("Deine Lieblingscocktails:", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))

                favoriteList[0].list.forEachIndexed { index, s ->
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
    }
    Spacer(modifier = Modifier.height(100.dp))
    Navigationbar(viewModel, listViewModel, navController)
}
