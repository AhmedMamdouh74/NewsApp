package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.SourcesItem

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveSourcesToDB(list: List<SourcesItem>)
    @Query("SELECT * FROM SourcesItem")
    suspend fun getSourcesFromDB():List<SourcesItem>
}