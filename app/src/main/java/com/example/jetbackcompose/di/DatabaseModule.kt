package com.example.jetbackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.jetbackcompose.database.NewsDao
import com.example.jetbackcompose.database.NewsLocalDataBase
import com.example.jetbackcompose.database.SourcesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsLocalDataBase {
        return Room.databaseBuilder(context, NewsLocalDataBase::class.java, "news DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDAO(
        newsLocalDatabase: NewsLocalDataBase
    ): NewsDao {
        return newsLocalDatabase.getNewsDao()
    }

    @Singleton
    @Provides
    fun provideSourcesDAO(
        newsLocalDatabase: NewsLocalDataBase
    ): SourcesDao {
        return newsLocalDatabase.getSourcesDao()
    }
}