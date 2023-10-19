package com.example.jetbackcompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetbackcompose.api.model.ArticlesItem

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNewsToDB(list:List<ArticlesItem>)
    @Query("SELECT * FROM ArticlesItem")
    suspend fun getNewsFromDB():List<ArticlesItem>
}