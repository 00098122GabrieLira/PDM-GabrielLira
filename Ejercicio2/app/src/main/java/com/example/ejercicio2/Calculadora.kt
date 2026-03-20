package com.example.ejercicio2

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
import com.example.ejercicio2.ui.theme.Ejercicio2Theme

class Calculadora : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicio2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          CalculadoraApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

var a by mutableStateOf(0)
var b by mutableStateOf(0)
var resultado by mutableStateOf(0)
var resultadoDivision by mutableStateOf(0.0)
var marca by mutableStateOf("TI-CAS")
val añosDeVida by mutableStateOf(2)
var precio by mutableStateOf(249.99)

var inputUsuario by mutableStateOf("")
var mensajeResultado by mutableStateOf("")
var opcionActual by mutableStateOf(0) // 0=menu principal, 1=ingresar valores

fun ingresarValores(valorA: Int, valorB: Int) {
  a = valorA
  b = valorB
  mensajeResultado = "Valores ingresados: a = $a, b = $b"
  opcionActual = 0
}

fun sumar() {
  resultado = a + b
  mensajeResultado = "La suma de $a con $b es $resultado"
}

fun restar() {
  resultado = a - b
  mensajeResultado = "La resta de $a menos $b es $resultado"
}

fun multiplicar() {
  resultado = a * b
  mensajeResultado = "La multiplicacion de $a con $b es $resultado"
}

fun dividir() {
  if (b != 0) {
    resultadoDivision = a.toDouble() / b.toDouble()
    mensajeResultado = "La division de $a entre $b es $resultadoDivision"
  } else {
    mensajeResultado = "Error: No se puede dividir entre cero"
  }
}

fun obtenerInfoCalculadora(): String {
  return "Marca: $marca\nAños de vida: $añosDeVida años\nPrecio: $$precio"
}

fun procesarOpcionPrincipal(opcion: Int) {
  when (opcion) {
    1 -> {
      opcionActual = 1
      mensajeResultado = "--- INGRESAR VALORES ---\nIngrese el valor de a:"
    }
    2 -> {
      if (a != 0 || b != 0) {
        sumar()
      } else {
        mensajeResultado = "Primero debe ingresar valores (opción 1)"
      }
    }
    3 -> {
      if (a != 0 || b != 0) {
        restar()
      } else {
        mensajeResultado = "Primero debe ingresar valores (opción 1)"
      }
    }
    4 -> {
      if (a != 0 || b != 0) {
        multiplicar()
      } else {
        mensajeResultado = "Primero debe ingresar valores (opción 1)"
      }
    }
    5 -> {
      if (a != 0 || b != 0) {
        dividir()
      } else {
        mensajeResultado = "Primero debe ingresar valores (opción 1)"
      }
    }
    6 -> {
      mensajeResultado = "Saliendo..."
    }
    else -> mensajeResultado = "Opción no válida"
  }
}

@Composable
fun CalculadoraApp(modifier: Modifier = Modifier) {
  var valorAInput by remember { mutableStateOf("") }
  var valorBInput by remember { mutableStateOf("") }
  var paso by remember { mutableStateOf(0) } // 0=esperando a, 1=esperando b
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      text = "Calculadora",
      color = Color.Cyan,
      style = MaterialTheme.typography.headlineMedium
    )
    
    Text(
      text = obtenerInfoCalculadora(),
      color = Color.Yellow,
      style = MaterialTheme.typography.bodyLarge
    )
    
    // Mostrar valores actuales si existen
    if (a != 0 || b != 0) {
      Text(
        text = "Valores actuales: a = $a, b = $b",
        color = Color.Green,
        style = MaterialTheme.typography.bodyMedium
      )
    }
    
    when (opcionActual) {
      0 -> {
        Text(
          text = "\n=== CALCULADORA ===",
          color = Color.White
        )
        Text(text = "1. Ingresar valores", color = Color.White)
        Text(text = "2. Sumar", color = Color.White)
        Text(text = "3. Restar", color = Color.White)
        Text(text = "4. Multiplicar", color = Color.White)
        Text(text = "5. Dividir", color = Color.White)
        Text(text = "6. Salir", color = Color.White)
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
              if (opcion == 6) {
                mensajeResultado = "Saliendo..."
              } else {
                procesarOpcionPrincipal(opcion)
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
      1 -> {
        if (paso == 0) {
          Text(
            text = mensajeResultado,
            color = Color.Yellow,
            style = MaterialTheme.typography.bodyLarge
          )
          
          OutlinedTextField(
            value = valorAInput,
            onValueChange = { valorAInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Valor de a") }
          )
          
          Button(
            onClick = {
              val numA = valorAInput.toIntOrNull()
              if (numA != null) {
                a = numA
                paso = 1
                mensajeResultado = "Ingrese el valor de b:"
                valorAInput = ""
              } else {
                mensajeResultado = "Por favor ingrese un número válido para a"
              }
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(text = "Aceptar")
          }
        } else {
          Text(
            text = mensajeResultado,
            color = Color.Yellow,
            style = MaterialTheme.typography.bodyLarge
          )
          
          OutlinedTextField(
            value = valorBInput,
            onValueChange = { valorBInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Valor de b") }
          )
          
          Button(
            onClick = {
              val numB = valorBInput.toIntOrNull()
              if (numB != null) {
                b = numB
                mensajeResultado = "Valores ingresados: a = $a, b = $b"
                opcionActual = 0
                paso = 0
                valorBInput = ""
              } else {
                mensajeResultado = "Por favor ingrese un número válido para b"
              }
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(text = "Aceptar")
          }
        }
      }
    }
    
    if (mensajeResultado.isNotEmpty() &&
      mensajeResultado != "--- INGRESAR VALORES ---\nIngrese el valor de a:" &&
      mensajeResultado != "Ingrese el valor de b:" &&
      opcionActual != 1) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
      ) {
        Text(
          text = mensajeResultado,
          color = if (mensajeResultado.contains("Error")) Color.Red else Color.Green,
          modifier = Modifier.padding(16.dp),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
    
    if (mensajeResultado == "Saliendo...") {
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
  }
}