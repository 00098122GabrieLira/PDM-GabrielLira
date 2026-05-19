package com.labo04.gala00098122.ui.screens.taskScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.labo04.gala00098122.model.Task

@Composable
fun TaskCard(task: Task) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = if (task.isCompleted) Color.LightGray else Color.White
    )
  ) {
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Text(
        text = "Titulo: ${task.title}",
        color = if (task.isCompleted) Color.Gray else Color.Black,
        modifier = Modifier.padding(bottom = 4.dp)
      )
      Text(
        text = "Descripcion: ${task.description}",
        color = if (task.isCompleted) Color.Gray else Color.DarkGray,
        modifier = Modifier.padding(bottom = 4.dp)
      )
      Text(
        text = "Fecha: ${task.endDate}",
        color = Color.Gray,
      )
      Text(
        text = "Completada: ${if(task.isCompleted)"Si" else "No"}",
        color = Color.Gray,
      )
    }
  }
}