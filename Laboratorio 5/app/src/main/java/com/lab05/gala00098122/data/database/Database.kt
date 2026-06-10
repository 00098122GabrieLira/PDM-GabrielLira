package com.lab05.gala00098122.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.lab05.gala00098122.data.dao.TaskDAO
import com.lab05.gala00098122.model.Task
import java.util.Date

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun taskDAO(): TaskDAO
}

class Converters {
  @TypeConverter
  fun fromTimestamp(value: Long?): Date? {
    return value?.let { Date(it) }
  }
  
  @TypeConverter
  fun dateToTimestamp(date: Date?): Long? {
    return date?.time
  }
}

class InitDatabase : Application() {
  companion object {
    lateinit var database: AppDatabase
  }
  
  override fun onCreate() {
    super.onCreate()
    database = Room.databaseBuilder(
      this,
      AppDatabase::class.java,
      "TaskDatabase"
    ).fallbackToDestructiveMigration(false).build()
  }
}