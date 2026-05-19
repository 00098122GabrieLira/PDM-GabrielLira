package com.labo04.gala00098122.navegacion

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.labo04.gala00098122.navegacion.routes.Routes
import com.labo04.gala00098122.ui.screens.taskScreen.TaskScreen
import com.labo04.gala00098122.ui.screens.welcomeScreen.WelcomeScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(Routes.Home)
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        WelcomeScreen(
          navigateToTask = {
            backStack.add(Routes.Task)
          },
        )
      }
      entry<Routes.Task> {
        TaskScreen(
          navigateBack = { backStack.removeLastOrNull() },
        )
      }
    },
    transitionSpec = {
      slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(500)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(500)
      )
    },
    popTransitionSpec = {
      slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(500)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(500)
      )
    },
    predictivePopTransitionSpec = {
      slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(250)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(250)
      )
    }
  )
}