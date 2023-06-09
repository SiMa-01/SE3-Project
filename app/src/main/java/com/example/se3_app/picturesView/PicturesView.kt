package com.example.se3_app.picturesView

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.navigateToDestination

val font = FontFamily(
    Font(resId = R.font.arciform)
)

@Composable
fun PicturesView(
    navController: NavController,
    viewModel: MainViewModel
) {
    PicrturesViewContent(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicrturesViewContent(
    navController: NavController,
    viewModel: MainViewModel
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Favorite,
        Icons.Filled.List
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
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

        val pic: Painter = painterResource(R.drawable.bild)
        val pic1: Painter = painterResource(R.drawable.bild1)
        val pic2: Painter = painterResource(R.drawable.bild2)
        val pic3: Painter = painterResource(R.drawable.bild3)
        val pic4: Painter = painterResource(R.drawable.bild4)
        val pic5: Painter = painterResource(R.drawable.bild5)
        val pic6: Painter = painterResource(R.drawable.bild6)
        val pic7: Painter = painterResource(R.drawable.bild7)

        Image(
            painter = pic,
            contentDescription = "Bild",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = pic1,
                contentDescription = "pic1",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = pic3,
                contentDescription = "pic3",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = pic7,
                contentDescription = "pic7",
                modifier = Modifier
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = pic2,
            contentDescription = "pic2",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = pic4,
                contentDescription = "pic4",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = pic5,
                contentDescription = "pic5",
                modifier = Modifier
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = pic6,
            contentDescription = "pic6",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomCenter)
    ) {
        BottomAppBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(icons[index], contentDescription = "Home") },
                    label = { Text(item) },
                    selected = selectedItem == 1,
                    onClick = {
                        if (index == 1) {
                            viewModel.getAllTastes()
                        }
                        selectedItem = index
                        navigateToDestination(navController, index)
                    }
                )
            }
        }
    }
}
