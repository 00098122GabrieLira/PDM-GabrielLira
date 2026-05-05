package com.lab03.gala00098122

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun useSensor(sensorType: Int): List<Float> {
  val context = LocalContext.current
  val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
  val sensor = sensorManager.getDefaultSensor(sensorType) ?: return emptyList()
  var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }
  
  DisposableEffect(sensorType) {
    val listener = object : SensorEventListener {
      override fun onSensorChanged(event: SensorEvent?) {
        event?.values?.let {
          sensorValues = it.toList()
        }
      }
      
      override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }
    
    sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
    
    onDispose {
      sensorManager.unregisterListener(listener)
    }
  }
  
  return sensorValues
}

@Composable
fun ProximitySensor(navigateToBack: () -> Unit) {
  val proximityValues = useSensor(Sensor.TYPE_PROXIMITY)
  
  Scaffold { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(innerPadding),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Sensor de Proximidad",
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = "Distancia:${proximityValues[0]} cm",
        color = Color.Black,
        fontSize = 18.sp
      )
    }
  }
}

