package com.example.ejercicio1

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
import com.example.ejercicio1.ui.theme.Ejercicio1Theme

class Computadora : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicio1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          ComputadoraApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

var ramGB by mutableStateOf(8)
var discoGB by mutableStateOf(570)
var sistemaOperativo by mutableStateOf("Windows")
var encendida by mutableStateOf(false)
var programas by mutableStateOf(mutableListOf<String>())

var inputUsuario by mutableStateOf("")
var mensajeResultado by mutableStateOf("")
var opcionActual by mutableStateOf(0) // 0=menu principal, 1=menu cambiar componentes, 2=cambiar RAM, 3=cambiar Disco, 4=cambiar SO

fun encender() {
  if (!encendida) {
    encendida = true
    programas = mutableListOf(
      "Notion 2026",
      "Facebook 2024",
      "Chrome 2025",
      "Word 2026",
      "Excel 2023"
    )
    mensajeResultado = "Computadora encendida"
  } else {
    mensajeResultado = "La computadora ya está encendida"
  }
  opcionActual = 0
}

fun apagar() {
  if (encendida) {
    encendida = false
    mensajeResultado = "Computadora apagada"
  } else {
    mensajeResultado = "La computadora ya está apagada"
  }
  opcionActual = 0
}

fun cambiarRAM(opcion: Int) {
  when (opcion) {
    1 -> {
      ramGB = 2
      mensajeResultado = "RAM actualizada a 2 GB"
    }
    2 -> {
      ramGB = 4
      mensajeResultado = "RAM actualizada a 4 GB"
    }
    3 -> {
      ramGB = 8
      mensajeResultado = "RAM actualizada a 8 GB"
    }
    4 -> {
      ramGB = 16
      mensajeResultado = "RAM actualizada a 16 GB"
    }
    else -> mensajeResultado = "Opción no válida"
  }
  opcionActual = 0
}

fun cambiarDisco(opcion: Int) {
  when (opcion) {
    1 -> {
      discoGB = 570
      mensajeResultado = "Disco actualizado a 570 GB"
    }
    2 -> {
      discoGB = 1024
      mensajeResultado = "Disco actualizado a 1 Terabyte (1024 GB)"
    }
    else -> mensajeResultado = "Opción no válida"
  }
  opcionActual = 0
}

fun cambiarSO(opcion: Int) {
  when (opcion) {
    1 -> {
      sistemaOperativo = "Linux"
      mensajeResultado = "Sistema operativo cambiado a Linux"
    }
    2 -> {
      sistemaOperativo = "Windows"
      mensajeResultado = "Sistema operativo cambiado a Windows"
    }
    else -> mensajeResultado = "Opción no válida"
  }
  opcionActual = 0
}

fun verComponentes(): String {
  val discoMostrar = if (discoGB == 1024) "1 TB" else "$discoGB GB"
  return "RAM: $ramGB GB\nDisco Duro: $discoMostrar\nSistema Operativo: $sistemaOperativo"
}

fun verProgramas(): String {
  val programas2026 = programas.filter { it.contains("2026") }
  return if (programas2026.isNotEmpty()) {
    "Programas del año 2026:\n${programas2026.joinToString("\n")}\nTotal: ${programas2026.size} programa(s)"
  } else {
    "No hay programas instalados del año 2026"
  }
}

fun procesarOpcionPrincipal(opcion: Int) {
  when (opcion) {
    1 -> encender()
    2 -> {
      if (encendida) {
        opcionActual = 1
        mensajeResultado = "--- CAMBIAR COMPONENTES ---\n1. Cambiar RAM\n2. Cambiar Disco Duro\n3. Cambiar Sistema Operativo\n4. Volver al menú principal\n\nSeleccione una opción:"
      } else {
        mensajeResultado = "La computadora debe estar encendida"
      }
    }
    3 -> {
      if (encendida) {
        mensajeResultado = verComponentes()
      } else {
        mensajeResultado = "Debes encender la computadora primero"
      }
    }
    4 -> {
      if (encendida) {
        mensajeResultado = verProgramas()
      } else {
        mensajeResultado = "Debes encender la computadora primero"
      }
    }
    5 -> apagar()
    else -> mensajeResultado = "Opción no válida"
  }
}

fun procesarSubmenuCambiarComponentes(opcion: Int) {
  when (opcion) {
    1 -> {
      opcionActual = 2
      mensajeResultado = "--- CAMBIAR RAM ---\n1. 2 GB\n2. 4 GB\n3. 8 GB\n4. 16 GB\n\nSeleccione una opción:"
    }
    2 -> {
      opcionActual = 3
      mensajeResultado = "--- CAMBIAR DISCO DURO ---\n1. 570 GB\n2. 1 Terabyte (1024 GB)\n\nSeleccione una opción:"
    }
    3 -> {
      opcionActual = 4
      mensajeResultado = "--- CAMBIAR SISTEMA OPERATIVO ---\n1. Linux\n2. Windows\n\nSeleccione una opción:"
    }
    4 -> {
      opcionActual = 0
      mensajeResultado = ""
    }
    else -> mensajeResultado = "Opción no válida"
  }
}

@Composable
fun ComputadoraApp(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      text = "Computadora",
      color = Color.Cyan,
      style = MaterialTheme.typography.headlineMedium
    )
    
    Text(
      text = "Estado: ${if (encendida) "Encendida" else "Apagada"}",
      color = if (encendida) Color.Green else Color.Red,
      style = MaterialTheme.typography.bodyLarge
    )
    
    when (opcionActual) {
      0 -> {
        Text(
          text = "\n=== MENU PRINCIPAL ===",
          color = Color.White
        )
        Text(text = "1. Encender computadora", color = Color.White)
        Text(text = "2. Cambiar componentes", color = Color.White)
        Text(text = "3. Ver componentes", color = Color.White)
        Text(text = "4. Ver programas instalados (año 2026)", color = Color.White)
        Text(text = "5. Apagar computadora", color = Color.White)
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
              procesarOpcionPrincipal(opcion)
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
      1 -> {
        Text(
          text = mensajeResultado,
          color = Color.Yellow,
          style = MaterialTheme.typography.bodyLarge
        )
        
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
              procesarSubmenuCambiarComponentes(opcion)
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
      2, 3, 4 -> {
        Text(
          text = mensajeResultado,
          color = Color.Yellow,
          style = MaterialTheme.typography.bodyLarge
        )
        
        OutlinedTextField(
          value = inputUsuario,
          onValueChange = { inputUsuario = it },
          modifier = Modifier.fillMaxWidth(),
          label = { Text("Ingrese opción") }
        )
        
        Button(
          onClick = {
            val subopcion = inputUsuario.toIntOrNull()
            if (subopcion != null) {
              when (opcionActual) {
                2 -> cambiarRAM(subopcion)
                3 -> cambiarDisco(subopcion)
                4 -> cambiarSO(subopcion)
              }
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
    }
    
    if (mensajeResultado.isNotEmpty() &&
      !mensajeResultado.contains("--- CAMBIAR COMPONENTES ---") &&
      !mensajeResultado.contains("--- CAMBIAR RAM ---") &&
      !mensajeResultado.contains("--- CAMBIAR DISCO DURO ---") &&
      !mensajeResultado.contains("--- CAMBIAR SISTEMA OPERATIVO ---")) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
      ) {
        Text(
          text = mensajeResultado,
          color = Color.Green,
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}