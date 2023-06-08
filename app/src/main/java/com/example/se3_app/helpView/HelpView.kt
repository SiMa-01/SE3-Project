package com.example.se3_app.helpView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.R
import com.example.se3_app.ui.theme.neueIdee

val font = FontFamily(
    Font(resId = R.font.arciform)
)


@Composable
fun HelpView(
    navController: NavController,
) {
    HelpViewContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpViewContent(
    navController: NavController,

    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {


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
        Spacer(modifier = Modifier.height(15.dp))




    }
}