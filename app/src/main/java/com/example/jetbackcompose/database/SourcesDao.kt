package com.example.jetbackcompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Sources

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveSourcesToDB(list: List<Sources>)
    @Query("SELECT * FROM Sources")
    suspend fun getSourcesFromDB():List<Sources>
}