package com.lab05.gala00098122

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.lab05.gala00098122.navegacion.MainNavigation
import com.lab05.gala00098122.ui.theme.Laboratorio5Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Laboratorio5Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MainNavigation(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}