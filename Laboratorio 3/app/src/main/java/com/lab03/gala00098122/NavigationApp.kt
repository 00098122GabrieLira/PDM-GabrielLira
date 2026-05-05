package com.lab03.gala00098122

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(Routes.Home)
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        HomeScreen(
          navigateToList = { backStack.add(Routes.Lista) },
          navigateToSensor = { backStack.add(Routes.Sensores) }
        )
      }
      entry<Routes.Lista> {
        ListaScreen(navigateToBack = { backStack.removeLastOrNull() })
      }
      entry<Routes.Sensores> {
        ProximitySensor(
          navigateToBack = { backStack.removeLastOrNull() }
        )
      }
    }
  )
}