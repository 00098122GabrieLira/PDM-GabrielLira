package com.lab05.gala00098122.ui.screens.taskScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab05.gala00098122.data.database.InitDatabase
import com.lab05.gala00098122.data.database.tables.Post
import com.lab05.gala00098122.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
  
  private val postDAO = InitDatabase.database.postDao()
  
  private val _tasks = MutableStateFlow<List<Task>>(emptyList())
  val tasks = _tasks.asStateFlow()
  
  init {
    loadTasks()
  }
  
  fun addTask(task: Task) {
    viewModelScope.launch {
      postDAO.insertPost(
        Post(
          id = task.id,
          title = task.title,
          content = task.description,
          endDate = task.endDate,
          isCompleted = task.isCompleted
        )
      )
    }
  }
  
  fun loadTasks() {
    viewModelScope.launch {
      postDAO.getAllPosts().collect { posts ->
        _tasks.value = posts.map { post ->
          Task(
            id = post.id,
            title = post.title,
            description = post.content,
            endDate = post.endDate,
            isCompleted = post.isCompleted
          )
        }
      }
    }
  }
}