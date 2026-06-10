package com.lab05.gala00098122.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lab05.gala00098122.data.database.tables.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
  @Query("SELECT * FROM posts")
  fun getAllPosts(): Flow<List<Post>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPost(post: Post)
}