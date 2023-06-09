package com.example.se3_app.ingredientsView

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.se3_app.ui.theme.Purple80
import com.example.se3_app.ui.theme.PurpleGrey40
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.cocktailSearchView.font
import com.example.se3_app.ui.theme.chipFarbe1
import com.example.se3_app.ui.theme.chipFarbe2
import com.example.se3_app.ui.theme.chipFarbe3
import com.example.se3_app.ui.theme.chipFarbe4
import com.example.se3_app.ui.theme.chipFarbe5
import com.example.se3_app.ui.theme.neueIdee
import com.google.android.material.chip.Chip
import kotlinx.serialization.descriptors.PrimitiveKind

//import androidx.compose.foundation.layout.FlowRow

val font = FontFamily(
    Font(resId = R.font.arciform)
)
var selectedFilters: MutableList<String> = emptyList<String>().toMutableList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsView(navController: NavController, ingredientsViewModel: MainViewModel) {

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
            ChipEachRow(navController, ingredientsViewModel, list = ingredients, tempList = tempList)
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipEachRow(navController: NavController, viewModel: MainViewModel, list: List<String>, tempList: Set<Int>) {

    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FlowRow(
            modifier = Modifier.padding(8.dp),
            Arrangement.spacedBy(5.dp)
        ) {
            list.forEachIndexed { index, s ->
                FilterChip(
                    selected = multipleChecked.contains(index),
                    onClick = {
                        multipleChecked = if (multipleChecked.contains(index))
                            multipleChecked.minus(index)
                        else
                            multipleChecked.plus(index)


                        selectedFilters = if (selectedFilters.contains(s))
                            selectedFilters.minus(s).toMutableList()
                        else
                            selectedFilters.plus(s).toMutableList()
                        //println("ItemsInList $selectedFilters ")
                        viewModel.selectedIngredients = selectedFilters
                        println("ItemsInViewModel ${viewModel.selectedIngredients} ")
                    },
                    label = {
                        Text(text = s, fontFamily = font)
                    },
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Color.Transparent,
                        borderWidth = if (multipleChecked.contains(index)) 0.dp else 2.dp
                    ),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor =
                        if (index % 6 == 0) chipFarbe1
                        else if (index % 4 == 0) chipFarbe2
                        else if (index % 3 == 0) chipFarbe3
                        else if (index % 2 == 0) chipFarbe4
                        else chipFarbe5,
                        selectedContainerColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = {
                        if (multipleChecked.contains(index)) {
                            Icon(Icons.Default.Check, contentDescription = "");
                        } else null
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
    // Der Suche Button
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        FloatingActionButton(
            onClick = {
                if (viewModel.cameFrom == 1) {
                    navController.navigate("CocktailSearchView")
                } else if (viewModel.cameFrom == 2) {
                    navController.navigate("HinzufuegenView")
                }
            },
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            containerColor = neueIdee
        ) {
            Text("Fertig", fontFamily = font,)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}

