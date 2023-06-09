package com.example.se3_app.helpView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.startView.navigateToDestination
import com.example.se3_app.ui.theme.neueIdee

val font = FontFamily(
    Font(resId = R.font.arciform)
)


@Composable
fun HelpView(
    navController: NavController, viewModel: MainViewModel
) {
    HelpViewContent(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpViewContent(
    navController: NavController,
    viewModel: MainViewModel
    ) {

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cocktails", "Merkliste", "Einkaufsliste")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Favorite, Icons.Filled.List
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
            IconButton(onClick = { navController.navigate("HelpView")


            }) {
                Icon(Icons.Filled.Info, contentDescription = "Search Icon")
            }
        })


        Text("MIX'N'FIX - alle Hifethemen", fontFamily = font, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(15.dp))

        FloatingActionButton(
            onClick = {  }, //andere Seite einfügen
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            Column( modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Info"
                )
                Text("Der Aufbau der App", fontFamily = font, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Bla", fontFamily = font, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(15.dp))


        FloatingActionButton(
            onClick = {  }, //andere Seite einfügen
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            Column( modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(Icons.Default.Person, contentDescription = "Konto")
                Text("Hast du Fragen zu deinem Nutzerkonto?", fontFamily = font, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Ich kann mich nicht einloggen!\nWie kann ich meine Kontaktdaten ändern?\nWie kann ich mich abmeldern?\nHier geht es zu allen 8 Fragen", fontFamily = font, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

        FloatingActionButton(
            onClick = {  navController.navigate("picturesView")},
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            Column( modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(Icons.Default.List, contentDescription = "Liste")
                Text("Über die Entwicklung", fontFamily = font, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("MIX'N'FIX startete als Projekt im Rahmen der Vorlesung Software Engineering an der DHBW Karlsruhe. Durch die Begeisterung an dem Thema entwickelte sich jedoch schnell eine Fastinazion. \nEntwicklerteam: Johanna Simml, Simon Mayer, Marcel Held, Felix Schempf und Jonas Fichtner-Pflaum", fontFamily = font, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
        Spacer(modifier = Modifier.height(100.dp))

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomCenter)
    ) {
        BottomAppBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(icon = { Icon(icons[index], contentDescription = "Home") },
                    label = { Text(item) },
                    selected = selectedItem == 1,
                    onClick = {
                        if (index == 1) {
                            viewModel.getAllTastes()
                        }
                        selectedItem = index
                        navigateToDestination(navController, index)
                    })
            }
        }

    }
}