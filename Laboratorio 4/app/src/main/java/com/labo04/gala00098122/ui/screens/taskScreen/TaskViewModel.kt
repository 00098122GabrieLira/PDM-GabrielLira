package com.labo04.gala00098122.ui.screens.taskScreen

import androidx.lifecycle.ViewModel
import com.labo04.gala00098122.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel: ViewModel() {
  private val _tasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
  val tasks = _tasks.asStateFlow()
  
  fun addTask(task: Task) {
    _tasks.value = _tasks.value.toMutableList().apply { add(task) }
  }
}