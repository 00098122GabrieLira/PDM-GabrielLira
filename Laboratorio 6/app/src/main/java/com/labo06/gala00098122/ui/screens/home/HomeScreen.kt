package com.labo06.gala00098122.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.labo06.gala00098122.scaffold.AppScaffold

@Composable
fun HomeScreen(
  navigateToRecipes: () -> Unit
) {
  AppScaffold(
    title = "Bienvenida"
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding).background(Color.LightGray),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(
        10.dp,
        alignment = Alignment.CenterVertically
      )
    ) {
      Text(
        text = "Lab06 - Ktor & MealDB",
        color = Color.Black
      )
      
      Button(
        onClick = { navigateToRecipes() },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Blue,
          contentColor = Color.White
        ),
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp)
      ) {
        Text(text = "Comenzar")
      }
    }
  }
}
