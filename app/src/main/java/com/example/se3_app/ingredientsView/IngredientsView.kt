package com.example.se3_app.ingredientsView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.se3_app.ui.theme.Purple80
import com.example.se3_app.ui.theme.PurpleGrey40
//import androidx.compose.foundation.layout.FlowRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsView(navController: NavController, ingredientsViewModel: IngredientsViewModel) {

    var ingredients = listOf("Apfel", "Banane", "Birne", "Orange", "Ananas")

    val tempList: Set<Int> = emptySet()

    if (ingredientsViewModel.loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ingredients = ingredientsViewModel.ingredients
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChipEachRow(list = ingredients, tempList = tempList)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipEachRow(
    list: List<String>,
    tempList: Set<Int>
) {

    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }

    Row(modifier = Modifier.padding(8.dp)) {
        list.forEachIndexed { index, s ->
            FilterChip(
                selected = multipleChecked.contains(index),
                onClick = {
                    multipleChecked = if (multipleChecked.contains(index))
                        multipleChecked.minus(index)
                    else
                        multipleChecked.plus(index)
                },
                label = {
                    Text(text = s)
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (!multipleChecked.contains(index)) PurpleGrey40 else Color.Transparent,
                    borderWidth = if (multipleChecked.contains(index)) 0.dp else 2.dp
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (multipleChecked.contains(index)) Purple80 else Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    if (multipleChecked.contains(index)) Icon(
                        Icons.Default.Check,
                        contentDescription = ""
                    ) else null
                }
            )
        }
    }


}