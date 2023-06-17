package com.example.se3_app.ingredientsView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.MainViewModel
import com.example.se3_app.R
import com.example.se3_app.searchCocktailView.font
import com.example.se3_app.ui.theme.cardColor1
import com.example.se3_app.ui.theme.cardColor2
import com.example.se3_app.ui.theme.cardColor3
import com.example.se3_app.ui.theme.cardColor4
import com.example.se3_app.ui.theme.cardColor5
import com.example.se3_app.ui.theme.cardColor6

val font = FontFamily(
    Font(resId = R.font.arciform)
)
var selectedFilters: MutableList<String> = emptyList<String>().toMutableList()

@Composable
fun IngredientsView(navController: NavController, ingredientsViewModel: MainViewModel) {
    val ingredients: List<String>
    val tempList: Set<Int> = emptySet()
    if (ingredientsViewModel.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ingredients = ingredientsViewModel.ingredients
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChipEachRow(
                navController,
                ingredientsViewModel,
                list = ingredients,
                tempList = tempList
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipEachRow(
    navController: NavController,
    viewModel: MainViewModel,
    list: List<String>,
    tempList: Set<Int>
) {
    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
        ) {
            Text(
                text = "Zutaten",
                fontFamily = font,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
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
                        selected = viewModel.selectedIngredients.contains(s),
                        onClick = {
                            multipleChecked = if (multipleChecked.contains(index)) {
                                multipleChecked.minus(index)
                            } else {
                                multipleChecked.plus(index)
                            }
                            selectedFilters = if (selectedFilters.contains(s)) {
                                selectedFilters.minus(s).toMutableList()
                            } else {
                                selectedFilters.plus(s).toMutableList()
                            }
                            viewModel.selectedIngredients = selectedFilters
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
                            if (index % 6 == 0) {
                                cardColor1
                            } else if (index % 4 == 0) {
                                cardColor2
                            } else if (index % 3 == 0) {
                                cardColor3
                            } else if (index % 2 == 0) {
                                cardColor4
                            } else {
                                cardColor5
                            },
                            selectedContainerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            if (viewModel.selectedIngredients.contains(s)) {
                                Icon(Icons.Default.Check, contentDescription = "")
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }

    // Search Button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(horizontal = 10.dp)
            .wrapContentSize(Alignment.BottomCenter)
    ) {
        FloatingActionButton(
            onClick = {
                println("cameFrom: " + viewModel.cameFrom)
                if (viewModel.cameFrom == 1) {
                    println("in der if ")
                    navController.navigate("SearchCocktailView")
                } else if (viewModel.cameFrom == 2) {
                    navController.navigate("newCocktailnView")
                }
            },
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            containerColor = cardColor6
        ) {
            Text("Fertig", fontFamily = font)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
