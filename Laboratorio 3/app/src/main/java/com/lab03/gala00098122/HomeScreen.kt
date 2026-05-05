package com.lab03.gala00098122

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  navigateToList: () -> Unit,
  navigateToSensor: () -> Unit,
) {
  Scaffold { innerPadding ->
    Column(
      modifier = modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
    ) {
      Text(
        text = "HomeScreen",
        color = Color.Black
      )
      
      Button(
        onClick = navigateToList,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Black,
          contentColor = Color.White
        )
      ) {
        Text(text = "Lista")
      }
      
      Button(
        onClick = navigateToSensor,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Black,
          contentColor = Color.White
        )
      ) {
        Text(text = "Sensores")
      }
    }
  }
  
}