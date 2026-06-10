package com.lab05.gala00098122.ui.screens.taskScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lab05.gala00098122.model.Task
import com.lab05.gala00098122.scafold.AppScaffold
import java.util.Date
import androidx.compose.foundation.lazy.items
import com.lab05.gala00098122.ui.screens.taskScreen.components.TaskCard

@Composable
fun TaskScreen(
  navigateBack: () -> Unit,
  viewModel: TaskViewModel = viewModel()
) {
  val tasks by viewModel.tasks.collectAsState()
  val context = LocalContext.current
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  
  fun Boton() {
    if (title.isNotBlank() && description.isNotBlank()) {
      val newTask = Task(
        id = (tasks.size + 1),
        title = title,
        description = description,
        endDate = Date(),
        isCompleted = false
      )
      viewModel.addTask(newTask)
      
      // Limpiar los campos
      title = ""
      description = ""
      
      Toast.makeText(
        context,
        "Tarea agregada con exito",
        Toast.LENGTH_SHORT
      ).show()
    } else {
      Toast.makeText(
        context,
        "Por favor completa todos los campos",
        Toast.LENGTH_SHORT
      ).show()
    }
  }
  
  AppScaffold(
    title = "Task",
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Volver",
          tint = Color.White
        )
      }
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        label = { Text(text = "Título") },
        placeholder = { Text(text = "Ingresa el título de la tarea") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Next
        ),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = Color.Black,
          focusedLabelColor = Color.Black,
          unfocusedLabelColor = Color.Black,
          focusedTextColor = Color.Black,
          unfocusedTextColor = Color.Black
        )
      )
      
      OutlinedTextField(
        value = description,
        onValueChange = { description = it },
        label = { Text(text = "Descripción") },
        placeholder = { Text(text = "Ingresa la descripción de la tarea") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Done
        ),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = Color.Black,
          focusedLabelColor = Color.Black,
          unfocusedLabelColor = Color.Black,
          focusedTextColor = Color.Black,
          unfocusedTextColor = Color.Black
        )
      )
      
      Button(
        onClick = { Boton() },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Blue,
          contentColor = Color.White
        ),
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp)
      ) {
        Text(text = "Agregar tarea")
      }
      
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(tasks) { task ->
          TaskCard(task = task)
          HorizontalDivider(modifier=Modifier.fillMaxWidth())
        }
      }
    }
  }
}