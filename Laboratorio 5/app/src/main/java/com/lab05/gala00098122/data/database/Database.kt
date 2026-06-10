package com.lab05.gala00098122.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lab05.gala00098122.data.dao.PostDao
import com.lab05.gala00098122.data.database.tables.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun postDao(): PostDao
}

class InitDatabase : Application() {
  companion object{
    lateinit var database: AppDatabase
  }
  
  override fun onCreate() {
    super.onCreate()
    
    database = Room.databaseBuilder(
      this,
      AppDatabase::class.java,
      "AppDatabase"
    ).fallbackToDestructiveMigration(false).build()
  }
}