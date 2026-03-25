package com.example.galauno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.galauno.ui.theme.GALAUNOTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      GALAUNOTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          ListaApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun ListaApp(modifier: Modifier = Modifier) {
  val nombres = remember { mutableStateListOf<String>() }
  val textoIngresado = remember { mutableStateOf("") }
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
      .padding(16.dp)
  ) {
    // Campo de texto para ingresar nombre
    TextField(
      value = textoIngresado.value,
      onValueChange = { textoIngresado.value = it },
      label = { Text("Ingrese un nombre") },
      modifier = Modifier.fillMaxWidth()
    )
    
    // Boton Guardar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
      contentAlignment = Alignment.Center
    ) {
      Button(
        onClick = {
          if (textoIngresado.value.isNotBlank()) {
            nombres.add(textoIngresado.value)
            textoIngresado.value = ""
          }
        },
        modifier = Modifier.size(width = 120.dp, height = 40.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF87CEEB),
          contentColor = Color.Black
        )
      ) {
        Text(text = "Guardar")
      }
    }
    
    // Fila con el título y boton limpiar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Listado de nombres y posición en la lista",
        color = Color.Black,
        modifier = Modifier.weight(1f)
      )
      
      // Botón Limpiar
      Button(
        onClick = {
          nombres.clear()
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF87CEEB),
          contentColor = Color.Black
        )
      ) {
        Text(text = "Limpiar")
      }
    }
    
    // Lista de nombres
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .border(
          width = 2.dp,
          color = Color(0xFF2196F3),
          shape = RoundedCornerShape(8.dp)
        )
        .background(Color.White)
        .padding(10.dp),
      contentAlignment = Alignment.TopStart
    ) {
      if (nombres.isEmpty()) {
        Text(
          text = "No hay nombres en la lista",
          color = Color.Gray,
          modifier = Modifier.padding(16.dp)
        )
      } else {
        LazyColumn {
          itemsIndexed(nombres) { index, nombre ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = nombre,
                color = Color.Black
              )
              Text(
                text = (index + 1).toString(),
                color = Color.Black
              )
            }
          }
        }
      }
    }
  }
}