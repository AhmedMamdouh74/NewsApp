package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.example.data.models.ArticlesItem
import com.example.data.models.SourcesItem


@Database(entities = [ArticlesItem::class, SourcesItem::class], version = 1)
abstract class NewsLocalDataBase : RoomDatabase() {
    abstract fun getSourcesDao():SourcesDao
    abstract fun getNewsDao():NewsDao
    companion object {
        private var Database: NewsLocalDataBase? = null
        fun init(context: Context) {
            if (Database==null) {
                Database = Room
                    .databaseBuilder(
                        context.applicationContext,
                        NewsLocalDataBase::class.java,
                        "news DB"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }

        }

        fun getInstance(): NewsLocalDataBase {
            return Database!!
        }
    }


}
