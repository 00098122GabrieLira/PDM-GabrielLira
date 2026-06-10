package com.lab05.gala00098122.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lab05.gala00098122.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
  @Query("SELECT * FROM tasks")
  fun getAllTasks(): Flow<List<Task>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTask(task: Task)
}