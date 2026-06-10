package com.lab05.gala00098122.ui.screens.taskScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab05.gala00098122.data.database.InitDatabase
import com.lab05.gala00098122.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
  
  private val taskDAO = InitDatabase.database.taskDAO()
  
  private val _tasks = MutableStateFlow<List<Task>>(emptyList())
  val tasks = _tasks.asStateFlow()
  
  init {
    loadTasks()
  }
  
  fun addTask(task: Task) {
    viewModelScope.launch {
      taskDAO.insertTask(
        Task(
          id = task.id,
          title = task.title,
          description = task.description,
          endDate = task.endDate,
          isCompleted = task.isCompleted
        )
      )
    }
  }
  
  fun loadTasks() {
    viewModelScope.launch {
      taskDAO.getAllTasks().collect { posts ->
        _tasks.value = posts.map { post ->
          Task(
            id = post.id,
            title = post.title,
            description = post.description,
            endDate = post.endDate,
            isCompleted = post.isCompleted
          )
        }
      }
    }
  }
}