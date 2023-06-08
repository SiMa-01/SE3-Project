package com.example.se3_app.pictures

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.R

val font = FontFamily(
    Font(resId = R.font.arciform)
)


@Composable
fun PicturesView(
    navController: NavController,
) {
    PicrturesViewContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicrturesViewContent(
    navController: NavController,

    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val Bild: Painter = painterResource(R.drawable.bild)
        val Bild1: Painter = painterResource(R.drawable.bild1)
        val Bild2: Painter = painterResource(R.drawable.bild2)
        val Bild3: Painter = painterResource(R.drawable.bild3)
        val Bild4: Painter = painterResource(R.drawable.bild4)
        val Bild5: Painter = painterResource(R.drawable.bild5)
        val Bild6: Painter = painterResource(R.drawable.bild6)
        val Bild7: Painter = painterResource(R.drawable.bild7)


        Image(
            painter = Bild,
            contentDescription = "Bild",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(5.dp)
        ){
            Image(
                painter = Bild1,
                contentDescription = "Bild1",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = Bild3,
                contentDescription = "Bild3",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = Bild7,
                contentDescription = "Bild7",
                modifier = Modifier
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = Bild2,
            contentDescription = "Bild2",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(5.dp)
        ){
            Image(
                painter = Bild4,
                contentDescription = "Bild4",
                modifier = Modifier
                    .height(200.dp)
            )
            Image(
                painter = Bild5,
                contentDescription = "Bild5",
                modifier = Modifier
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = Bild6,
            contentDescription = "Bild6",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

    }
}