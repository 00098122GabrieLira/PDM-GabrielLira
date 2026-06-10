package com.lab05.gala00098122.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "posts")
data class Post(
  @PrimaryKey val id: Int,
  val title: String,
  val content: String,
  val endDate: Date = Date(),
  val isCompleted: Boolean
)