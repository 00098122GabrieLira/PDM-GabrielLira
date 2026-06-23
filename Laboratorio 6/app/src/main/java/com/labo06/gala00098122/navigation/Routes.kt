package com.labo06.gala00098122.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Home : Routes()
  
  
  @Serializable
  data object Recipes : Routes()
}