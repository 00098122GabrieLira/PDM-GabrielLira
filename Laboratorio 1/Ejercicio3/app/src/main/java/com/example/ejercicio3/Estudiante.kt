package com.example.ejercicio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import com.example.ejercicio3.ui.theme.Ejercicio3Theme

class Estudiante : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicio3Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          EstudianteApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

var Ciclo01 by mutableStateOf(mutableListOf<String>())
var inputUsuario by mutableStateOf("")
var mensajeResultado by mutableStateOf("")
var opcionActual by mutableStateOf(0) // 0=menu principal, 1=mostrar moviles, 2=mostrar todos

fun generarCarnet(): String {
  val numeroEstudiante = String.format("%06d", Random.nextInt(1, 999999))
  val año = String.format("%02d", Random.nextInt(1, 27))
  return "$numeroEstudiante$año"
}

fun agregarEstudiantes() {
  Ciclo01 = mutableListOf(
    "Ana García,${generarCarnet()},Programación de Dispositivos Móviles",
    "Carlos López,${generarCarnet()},Programación de Dispositivos Móviles",
    "María Rodríguez,${generarCarnet()},Programación de Dispositivos Móviles",
    "Juan Pérez,${generarCarnet()},Análisis Numérico",
    "Laura Martínez,${generarCarnet()},Análisis Numérico",
    "Pedro Sánchez,${generarCarnet()},Análisis Numérico",
    "Diana Torres,${generarCarnet()},Análisis Numérico"
  )
}

fun obtenerEstudiantesDispositivosMoviles(): String {
  val resultado = mutableListOf<String>()
  for (estudiante in Ciclo01) {
    if (estudiante.contains("Programación de Dispositivos Móviles")) {
      val datos = estudiante.split(",")
      resultado.add("${datos[0]} - Carnet: ${datos[1]}")
    }
  }
  
  return if (resultado.isNotEmpty()) {
    "--- ESTUDIANTES DE PROGRAMACIÓN DE DISPOSITIVOS MÓVILES ---\n${resultado.joinToString("\n")}\nTotal: ${resultado.size} estudiante(s)"
  } else {
    "No hay estudiantes inscritos en esta materia"
  }
}

fun obtenerTodosLosEstudiantes(): String {
  val moviles = mutableListOf<String>()
  val analisis = mutableListOf<String>()
  
  for (estudiante in Ciclo01) {
    if (estudiante.contains("Programación de Dispositivos Móviles")) {
      val datos = estudiante.split(",")
      moviles.add("${datos[0]} - Carnet: ${datos[1]}")
    } else {
      val datos = estudiante.split(",")
      analisis.add("${datos[0]} - Carnet: ${datos[1]}")
    }
  }
  
  return "--- ESTUDIANTES DEL CICLO 01 ---\n\nProgramación de Dispositivos Móviles (${moviles.size} estudiantes):\n${moviles.joinToString("\n")}\n\nAnálisis Numérico (${analisis.size} estudiantes):\n${analisis.joinToString("\n")}"
}

fun procesarOpcion(opcion: Int) {
  when (opcion) {
    1 -> {
      opcionActual = 1
      mensajeResultado = obtenerEstudiantesDispositivosMoviles()
    }
    2 -> {
      opcionActual = 2
      mensajeResultado = obtenerTodosLosEstudiantes()
    }
    3 -> {
      mensajeResultado = "Saliendo..."
    }
    else -> mensajeResultado = "Opción no válida"
  }
}

@Composable
fun EstudianteApp(modifier: Modifier = Modifier) {
  LaunchedEffect(Unit) {
    agregarEstudiantes()
  }
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      text = "Estudiante",
      color = Color.Cyan,
      style = MaterialTheme.typography.headlineMedium
    )
    
    when (opcionActual) {
      0 -> {
        Text(
          text = "\n== SISTEMA DE ESTUDIANTES - CICLO 01 ==",
          color = Color.White
        )
        Text(text = "1. Mostrar estudiantes de Dispositivos Móviles", color = Color.White)
        Text(text = "2. Mostrar todos los estudiantes", color = Color.White)
        Text(text = "3. Salir", color = Color.White)
        Text(text = "\nEscoja una opcion:", color = Color.Yellow)
        
        OutlinedTextField(
          value = inputUsuario,
          onValueChange = { inputUsuario = it },
          modifier = Modifier.fillMaxWidth(),
          label = { Text("Ingrese opción") }
        )
        
        Button(
          onClick = {
            val opcion = inputUsuario.toIntOrNull()
            if (opcion != null) {
              procesarOpcion(opcion)
            } else {
              mensajeResultado = "Por favor ingrese un número válido"
            }
            inputUsuario = ""
          },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(text = "Aceptar")
        }
      }
      1, 2 -> {
        Card(
          modifier = Modifier.fillMaxWidth(),
          colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
          Text(
            text = mensajeResultado,
            color = Color.Green,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
          )
        }
        
        Button(
          onClick = {
            opcionActual = 0
            mensajeResultado = ""
          },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(text = "Volver al menú principal")
        }
      }
    }
    
    if (mensajeResultado == "Saliendo..." && opcionActual == 0) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
      ) {
        Text(
          text = mensajeResultado,
          color = Color.Yellow,
          modifier = Modifier.padding(16.dp)
        )
      }
    }
    
    if (mensajeResultado.isNotEmpty() &&
      mensajeResultado != "Saliendo..." &&
      opcionActual == 0 &&
      mensajeResultado != "Por favor ingrese un número válido" &&
      mensajeResultado != "Opción no válida") {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
      ) {
        Text(
          text = mensajeResultado,
          color = when {
            mensajeResultado.contains("no válida") -> Color.Red
            mensajeResultado.contains("válido") -> Color.Red
            else -> Color.Green
          },
          modifier = Modifier.padding(16.dp)
        )
      }
    }
    
    if (mensajeResultado == "Por favor ingrese un número válido" || mensajeResultado == "Opción no válida") {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
      ) {
        Text(
          text = mensajeResultado,
          color = Color.Red,
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}